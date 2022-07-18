package dev.mikchan.mcnp.chat.events.listener

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.events.event.MCNChatEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener

internal class MCNCListener(private val plugin: ChatPlugin) : Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    fun onMCNCEChatEvent(event: MCNChatEvent) {
        plugin.server.scheduler.scheduleSyncDelayedTask(plugin) {
            for (recipient in event.recipients) {
                recipient.sendMessage(event.sender.uniqueId, event.formattedMessage)
            }

            plugin.server.consoleSender.sendMessage(event.formattedMessage)
        }
    }
}
