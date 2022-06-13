package dev.mikchan.chat.events

import dev.mikchan.chat.Chat

internal class DefaultEventManagerCreator : IEventManagerCreator {
    override fun create(plugin: Chat): IEventManager {
        return DefaultEventManager(plugin)
    }
}