package dev.mikchan.chat.events.listener

import dev.mikchan.chat.Chat
import dev.mikchan.chat.events.event.MCNChatEvent
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import kotlin.streams.asSequence

internal class ChatListener(private val plugin: Chat) : Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    fun onAsyncMessage(event: AsyncPlayerChatEvent) {
        event.isCancelled = true

        var msg = event.message.trim()
        val isGlobal = !plugin.config.enableLocal || msg.startsWith(plugin.config.globalPrefix)

        if ((!event.player.hasPermission("mcn.chat.global") && isGlobal) || (!event.player.hasPermission("mcn.chat.global") && isGlobal)) {
            plugin.server.scheduler.scheduleSyncDelayedTask(plugin) {
                event.player.sendMessage(ChatColor.RED.toString() + "You have no permission to do that!")
            }

            return
        }

        if (isGlobal && plugin.config.enableLocal) {
            msg = msg.drop(plugin.config.globalPrefix.length)
        }

        val recipients = mutableSetOf<Player>()
        if (isGlobal) {
            recipients.addAll(plugin.server.onlinePlayers)
        } else {
            val r = plugin.config.localRadius

            recipients.addAll(plugin.server.onlinePlayers.stream()
                .filter { player -> player.world == event.player.world && player.location.distance(event.player.location) < r }
                .asSequence())
        }

        plugin.server.scheduler.scheduleSyncDelayedTask(plugin) {
            plugin.server.pluginManager.callEvent(
                MCNChatEvent(
                    event.player, recipients, isGlobal, msg, if (isGlobal) plugin.formatter.formatGlobal(
                        event.player, msg
                    ) else plugin.formatter.formatLocal(event.player, msg)
                )
            )
        }
    }
}