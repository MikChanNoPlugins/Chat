package dev.mikchan.mcnp.chat.config

import dev.mikchan.mcnp.chat.ChatPlugin

interface IConfigCreator {
    fun create(plugin: ChatPlugin): IConfig
}
