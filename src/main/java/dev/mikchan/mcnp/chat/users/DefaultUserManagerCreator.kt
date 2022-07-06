package dev.mikchan.mcnp.chat.users

import dev.mikchan.mcnp.chat.Chat

internal class DefaultUserManagerCreator : IUserManagerCreator {
    override fun create(plugin: Chat): IUserManager {
        return DefaultUserManager(plugin)
    }
}