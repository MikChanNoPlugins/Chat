package dev.mikchan.mcnp.chat.keys

import dev.mikchan.mcnp.chat.ChatPlugin

internal class DefaultKeysCreator : IKeysCreator {
    override fun create(plugin: ChatPlugin): IKeys {
        return DefaultKeys(plugin)
    }
}
