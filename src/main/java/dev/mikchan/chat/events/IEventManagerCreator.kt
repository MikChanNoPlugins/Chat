package dev.mikchan.chat.events

import dev.mikchan.chat.Chat

interface IEventManagerCreator {
    fun create(plugin: Chat): IEventManager
}
