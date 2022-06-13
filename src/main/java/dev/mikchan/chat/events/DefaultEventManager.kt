package dev.mikchan.chat.events

import dev.mikchan.chat.Chat
import dev.mikchan.chat.events.listener.ChatListener
import dev.mikchan.chat.events.listener.MCNCListener
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener

internal class DefaultEventManager(private val plugin: Chat) : IEventManager {
    private val listeners: List<Listener> = listOf(
        ChatListener(plugin),
        MCNCListener(plugin)
    )

    override fun unregister() {
        HandlerList.unregisterAll(plugin)
    }

    override fun register() {
        unregister()

        val pluginManager = plugin.server.pluginManager
        for (listener in listeners) {
            pluginManager.registerEvents(listener, plugin)
        }
    }
}