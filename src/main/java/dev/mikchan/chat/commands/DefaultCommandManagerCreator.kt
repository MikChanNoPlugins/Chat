package dev.mikchan.chat.commands

import dev.mikchan.chat.Chat

internal class DefaultCommandManagerCreator : ICommandManagerCreator {
    override fun create(plugin: Chat): ICommandManager {
        return DefaultCommandManager(plugin)
    }
}