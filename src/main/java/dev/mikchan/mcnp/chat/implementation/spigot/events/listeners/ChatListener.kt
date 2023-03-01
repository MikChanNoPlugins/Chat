package dev.mikchan.mcnp.chat.implementation.spigot.events.listeners

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.contract.events.MCNChatEvent
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.AsyncPlayerChatPreviewEvent

internal class ChatListener(private val plugin: ChatPlugin) : Listener {
    private class NoPermissionException : Exception()
    private class EmptyMessageException : Exception()

    private fun convertGlobal(event: AsyncPlayerChatEvent, isPreview: Boolean, msg: String): MCNChatEvent {
        if (msg.isEmpty()) throw EmptyMessageException()
        if (!event.player.hasPermission("mcn.chat.global") && !isPreview) throw NoPermissionException()

        val recipients = plugin.server.onlinePlayers.toSet()
        val formattedMessage = plugin.formatter.formatGlobal(event.player, "%2\$s")

        return MCNChatEvent(event.player, recipients, true, msg, formattedMessage, isPreview, event.isAsynchronous)
    }

    private fun convertLocal(event: AsyncPlayerChatEvent, isPreview: Boolean, msg: String): MCNChatEvent {
        if (msg.isEmpty()) throw EmptyMessageException()
        if (!event.player.hasPermission("mcn.chat.local") && !isPreview) throw NoPermissionException()

        val localRadius = plugin.config.localRadius
        val recipients = plugin.server.onlinePlayers.toList().filter { player -> player.world == event.player.world }
            .filter { player -> player.location.distance(event.player.location) < localRadius }.toSet()

        val formattedMessage = plugin.formatter.formatLocal(event.player, "%2\$s")

        return MCNChatEvent(event.player, recipients, false, msg, formattedMessage, isPreview, event.isAsynchronous)
    }

    private fun convert(event: AsyncPlayerChatEvent, isPreview: Boolean): MCNChatEvent {
        val msg = event.message.trim()

        return if (!plugin.config.enableLocal) convertGlobal(event, isPreview, msg)
        else if (msg.startsWith(plugin.config.globalPrefix)) convertGlobal(
            event, isPreview, msg.drop(plugin.config.globalPrefix.length).trim()
        )
        else convertLocal(event, isPreview, msg)
    }

    private fun handleChatEvent(event: AsyncPlayerChatEvent, isPreview: Boolean): Boolean {
        try {
            val mcncEvent = convert(event, isPreview)
            plugin.server.pluginManager.callEvent(mcncEvent)

            if (mcncEvent.isCancelled) {
                return false
            }

            if (plugin.config.substituteEvents) {
                if (!isPreview) {
                    plugin.server.scheduler.scheduleSyncDelayedTask(plugin) {
                        val fMsg = String.format(mcncEvent.formattedMessage, mcncEvent.sender, mcncEvent.message)
                        plugin.server.consoleSender.sendMessage(fMsg)
                        for (recipient in mcncEvent.recipients) {
                            recipient.sendMessage(mcncEvent.sender.uniqueId, fMsg)
                        }
                    }
                }

                return false
            } else {
                event.message = mcncEvent.message
                event.format = mcncEvent.formattedMessage
                event.recipients.clear()
                event.recipients.addAll(mcncEvent.recipients)

                return true
            }
        } catch (ex: EmptyMessageException) {
            return false
        } catch (ex: NoPermissionException) {
            plugin.server.scheduler.scheduleSyncDelayedTask(plugin) {
                event.player.sendMessage("${ChatColor.RED}You have no permission to do that!")
            }

            return false
        }
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
