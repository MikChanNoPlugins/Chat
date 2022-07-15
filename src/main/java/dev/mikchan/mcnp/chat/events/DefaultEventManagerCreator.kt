package dev.mikchan.mcnp.chat.events

import dev.mikchan.mcnp.chat.ChatPlugin

internal class DefaultEventManagerCreator : IEventManagerCreator {
    override fun create(plugin: ChatPlugin): IEventManager {
        return DefaultEventManager(plugin)
    }
}
