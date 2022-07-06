package dev.mikchan.mcnp.chat.events

import dev.mikchan.mcnp.chat.Chat

interface IEventManagerCreator {
    fun create(plugin: Chat): IEventManager
}
