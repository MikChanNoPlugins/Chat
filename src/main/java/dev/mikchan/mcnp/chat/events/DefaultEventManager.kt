package dev.mikchan.mcnp.chat.events

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.events.listener.ChatListener
import dev.mikchan.mcnp.chat.events.listener.MCNCListener
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener

internal class DefaultEventManager(private val plugin: ChatPlugin) : IEventManager {
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