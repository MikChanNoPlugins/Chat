package dev.mikchan.chat.config

import dev.mikchan.chat.Chat

interface IConfigCreator {
    fun create(plugin: Chat): IConfig
}
