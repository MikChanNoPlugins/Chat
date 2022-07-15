package dev.mikchan.mcnp.chat.users

import dev.mikchan.mcnp.chat.ChatPlugin

internal class DefaultUserManagerCreator : IUserManagerCreator {
    override fun create(plugin: ChatPlugin): IUserManager {
        return DefaultUserManager(plugin)
    }
}
