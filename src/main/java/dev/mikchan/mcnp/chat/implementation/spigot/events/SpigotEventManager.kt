package dev.mikchan.mcnp.chat.implementation.spigot.events

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.contract.events.IEventManager
import dev.mikchan.mcnp.chat.implementation.spigot.events.listeners.ChatListener
import dev.mikchan.mcnp.chat.implementation.spigot.events.listeners.MCNCListener
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener

internal class SpigotEventManager(private val plugin: ChatPlugin) : IEventManager {
    private val listeners: List<Listener> = listOf(
        ChatListener(plugin),
        MCNCListener(plugin),
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
