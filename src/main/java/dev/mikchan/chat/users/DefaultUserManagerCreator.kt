package dev.mikchan.chat.users

import dev.mikchan.chat.Chat

internal class DefaultUserManagerCreator : IUserManagerCreator {
    override fun create(plugin: Chat): IUserManager {
        return DefaultUserManager(plugin)
    }
}