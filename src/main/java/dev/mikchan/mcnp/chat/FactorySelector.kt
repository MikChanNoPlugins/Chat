package dev.mikchan.mcnp.chat

import dev.mikchan.mcnp.chat.interfaces.IChatPluginFactory
import dev.mikchan.mcnp.chat.implementation.spigot.SpigotChatPluginFactory

object FactorySelector {
    fun selectFactory(plugin: ChatPlugin): IChatPluginFactory {
        return SpigotChatPluginFactory(plugin)
    }
}
