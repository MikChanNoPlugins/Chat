package dev.mikchan.mcnp.chat.implementation.base.event

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.contract.events.IEventManager
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener


internal open class BaseEventManager(
    private val plugin: ChatPlugin,
    listenerBuilder: (plugin: ChatPlugin) -> List<Listener>,
) : IEventManager {
    private val listeners: List<Listener> = listenerBuilder(plugin)

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
