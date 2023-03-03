package dev.mikchan.mcnp.chat.implementation.spigot.events.listeners

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.implementation.base.event.listeners.BaseChatListener
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.AsyncPlayerChatPreviewEvent

internal class ChatListener(plugin: ChatPlugin) : BaseChatListener(plugin), Listener {
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    fun onAsyncMessage(event: AsyncPlayerChatEvent) {
        if (!handleChatEvent(event, false)) event.isCancelled = true
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    fun onAsyncPlayerChatPreview(event: AsyncPlayerChatPreviewEvent) {
        if (!handleChatEvent(event, true)) event.isCancelled = true
    }
}
