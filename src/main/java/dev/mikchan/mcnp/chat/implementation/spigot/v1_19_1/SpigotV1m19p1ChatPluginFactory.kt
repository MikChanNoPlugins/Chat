package dev.mikchan.mcnp.chat.implementation.spigot.v1_19_1

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.contract.events.IEventManager
import dev.mikchan.mcnp.chat.implementation.spigot.v1_19.SpigotV1m19ChatPluginFactory
import dev.mikchan.mcnp.chat.implementation.spigot.v1_19_1.events.SpigotV1m19p1EventManager

internal open class SpigotV1m19p1ChatPluginFactory(private val plugin: ChatPlugin) :
    SpigotV1m19ChatPluginFactory(plugin) {
    override fun createEventManager(): IEventManager {
        return SpigotV1m19p1EventManager(plugin)
    }
}
