package dev.mikchan.chat.events.listener

import dev.mikchan.chat.Chat
import dev.mikchan.chat.events.event.MCNChatEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener

internal class MCNCListener(private val plugin: Chat) : Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    fun onMCNCEChatEvent(event: MCNChatEvent) {
        for (recipient in event.recipients) {
            recipient.sendMessage(event.formattedMessage)
        }

        plugin.server.consoleSender.sendMessage(event.formattedMessage)
    }
}