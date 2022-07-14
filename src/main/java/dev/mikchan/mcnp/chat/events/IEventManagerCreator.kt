package dev.mikchan.mcnp.chat.events

import dev.mikchan.mcnp.chat.ChatPlugin

interface IEventManagerCreator {
    fun create(plugin: ChatPlugin): IEventManager
}
