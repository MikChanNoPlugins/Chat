package dev.mikchan.mcnp.chat.config

import dev.mikchan.mcnp.chat.Chat

interface IConfigCreator {
    fun create(plugin: Chat): IConfig
}
