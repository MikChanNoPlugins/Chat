package dev.mikchan.mcnp.chat.commands

import dev.mikchan.mcnp.chat.ChatPlugin

interface ICommandManagerCreator {
    fun create(plugin: ChatPlugin): ICommandManager
}