package dev.mikchan.mcnp.chat.formatting

import dev.mikchan.mcnp.chat.ChatPlugin

interface IFormatterCreator {
    fun create(plugin: ChatPlugin): IFormatter
}