package dev.mikchan.chat.listeners

import dev.mikchan.chat.Chat
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

internal class ChatListener(private val plugin: Chat) : Listener {
    @EventHandler
    fun onAsyncPlayerChatEvent(event: AsyncPlayerChatEvent) {
        event.isCancelled = true

        val msg = event.message
        Bukkit.getServer().scheduler.scheduleSyncDelayedTask(plugin) {
            plugin.logger.info(msg)
        }
    }
}
