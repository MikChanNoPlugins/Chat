package dev.mikchan.mcnp.chat.formatting

import dev.mikchan.mcnp.chat.Chat

interface IFormatterCreator {
    fun create(plugin: Chat): IFormatter
}