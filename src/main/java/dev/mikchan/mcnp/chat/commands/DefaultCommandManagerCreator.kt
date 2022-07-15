package dev.mikchan.mcnp.chat.commands

import dev.mikchan.mcnp.chat.ChatPlugin

internal class DefaultCommandManagerCreator : ICommandManagerCreator {
    override fun create(plugin: ChatPlugin): ICommandManager {
        return DefaultCommandManager(plugin)
    }
}
