package dev.mikchan.mcnp.chat.keys

import dev.mikchan.mcnp.chat.ChatPlugin

interface IKeysCreator {
    fun create(plugin: ChatPlugin): IKeys
}
