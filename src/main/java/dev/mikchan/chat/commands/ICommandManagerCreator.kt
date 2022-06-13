package dev.mikchan.chat.commands

import dev.mikchan.chat.Chat

interface ICommandManagerCreator {
    fun create(plugin: Chat): ICommandManager
}