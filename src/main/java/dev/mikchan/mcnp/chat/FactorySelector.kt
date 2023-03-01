package dev.mikchan.mcnp.chat

import dev.mikchan.mcnp.chat.contract.IChatPluginFactory
import dev.mikchan.mcnp.chat.implementation.spigot.SpigotChatPluginFactory

object FactorySelector {
    fun selectFactory(plugin: ChatPlugin): IChatPluginFactory {
        return SpigotChatPluginFactory(plugin)
    }
}
