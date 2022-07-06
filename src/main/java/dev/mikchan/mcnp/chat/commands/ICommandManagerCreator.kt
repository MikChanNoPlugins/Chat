package dev.mikchan.mcnp.chat.commands

import dev.mikchan.mcnp.chat.Chat

interface ICommandManagerCreator {
    fun create(plugin: Chat): ICommandManager
}