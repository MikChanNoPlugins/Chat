package dev.mikchan.chat.formatting

import dev.mikchan.chat.Chat

interface IFormatterCreator {
    fun create(plugin: Chat): IFormatter
}