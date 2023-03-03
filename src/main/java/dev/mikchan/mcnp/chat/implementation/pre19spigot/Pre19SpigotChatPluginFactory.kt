package dev.mikchan.mcnp.chat.implementation.pre19spigot

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.contract.events.IEventManager
import dev.mikchan.mcnp.chat.implementation.pre19spigot.events.Pre19SpigotEventManager
import dev.mikchan.mcnp.chat.implementation.spigot.SpigotChatPluginFactory

internal class Pre19SpigotChatPluginFactory(val plugin: ChatPlugin) : SpigotChatPluginFactory(plugin) {
    override fun createEventManager(): IEventManager {
        return Pre19SpigotEventManager(plugin)
    }
}
