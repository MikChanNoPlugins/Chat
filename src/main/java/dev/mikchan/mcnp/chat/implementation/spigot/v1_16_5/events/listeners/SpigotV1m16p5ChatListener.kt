package dev.mikchan.mcnp.chat.implementation.spigot.v1_16_5.events.listeners

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.implementation.base.event.listeners.BaseChatListener
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

internal class SpigotV1m16p5ChatListener(plugin: ChatPlugin) : BaseChatListener(plugin), Listener {
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    fun onAsyncMessage(event: AsyncPlayerChatEvent) {
        if (!handleChatEvent(event, false)) event.isCancelled = true
    }
}
