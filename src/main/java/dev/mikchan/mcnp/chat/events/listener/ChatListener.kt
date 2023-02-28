package dev.mikchan.mcnp.chat.events.listener

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.events.event.MCNChatEvent
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.AsyncPlayerChatPreviewEvent
import kotlin.streams.asSequence

internal class ChatListener(private val plugin: ChatPlugin) : Listener {
    private fun checkPermissions(player: Player, isGlobal: Boolean): Boolean {
        if (!player.hasPermission("mcn.chat.global") && isGlobal) return false
        if (!player.hasPermission("mcn.chat.local") && !isGlobal) return false
        return true
    }

    private fun handleChatEvent(event: AsyncPlayerChatEvent, isPreview: Boolean): Boolean {
        var msg = event.message.trim()
        val isGlobal = !plugin.config.enableLocal || msg.startsWith(plugin.config.globalPrefix)

        if (!isPreview && !checkPermissions(event.player, isGlobal)) {
            plugin.server.scheduler.scheduleSyncDelayedTask(plugin) {
                event.player.sendMessage("${ChatColor.RED}You have no permission to do that!")
            }

            return false
        }

        if (isGlobal && plugin.config.enableLocal) {
            msg = msg.drop(plugin.config.globalPrefix.length).trim()
        }

        if (msg.isEmpty()) return false

        val recipients = mutableSetOf<Player>()
        if (isGlobal) {
            recipients.addAll(plugin.server.onlinePlayers)
        } else {
            val r = plugin.config.localRadius

            recipients.addAll(plugin.server.onlinePlayers.stream().filter { player ->
                player.world == event.player.world && player.location.distance(event.player.location) < r
            }.asSequence())
        }

        val mcncEvent = MCNChatEvent(
            event.player, recipients, isGlobal, msg, if (isGlobal) plugin.formatter.formatGlobal(
                event.player, "%2\$s"
            ) else plugin.formatter.formatLocal(event.player, "%2\$s"), isPreview, event.isAsynchronous
        )

        plugin.server.pluginManager.callEvent(mcncEvent)

        if (mcncEvent.isCancelled) {
            return false
        }

        event.message = msg
        event.format = mcncEvent.formattedMessage
        event.recipients.clear()
        event.recipients.addAll(mcncEvent.recipients)

        return true
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    fun onAsyncMessage(event: AsyncPlayerChatEvent) {
        if (!handleChatEvent(event, false)) event.isCancelled = true
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    fun onAsyncPlayerChatPreview(event: AsyncPlayerChatPreviewEvent) {
        if (!handleChatEvent(event, true)) event.isCancelled = true
    }
}
