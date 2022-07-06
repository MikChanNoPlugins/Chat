package dev.mikchan.mcnp.chat.events

import dev.mikchan.mcnp.chat.Chat

internal class DefaultEventManagerCreator : IEventManagerCreator {
    override fun create(plugin: Chat): IEventManager {
        return DefaultEventManager(plugin)
    }
}