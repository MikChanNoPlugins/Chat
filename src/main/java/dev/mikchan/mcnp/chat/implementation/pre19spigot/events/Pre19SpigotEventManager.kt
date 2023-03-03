package dev.mikchan.mcnp.chat.implementation.pre19spigot.events

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.contract.events.IEventManager
import dev.mikchan.mcnp.chat.implementation.pre19spigot.events.listeners.Pre19SpigotChatListener
import dev.mikchan.mcnp.chat.implementation.spigot.events.listeners.MCNCListener
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener

internal class Pre19SpigotEventManager(private val plugin: ChatPlugin) : IEventManager {
    private val listeners: List<Listener> = listOf(
        Pre19SpigotChatListener(plugin),
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
