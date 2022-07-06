package dev.mikchan.mcnp.chat.commands

import dev.mikchan.mcnp.chat.Chat

internal class DefaultCommandManagerCreator : ICommandManagerCreator {
    override fun create(plugin: Chat): ICommandManager {
        return DefaultCommandManager(plugin)
    }
}