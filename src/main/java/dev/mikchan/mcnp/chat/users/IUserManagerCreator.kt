package dev.mikchan.mcnp.chat.users

import dev.mikchan.mcnp.chat.Chat

interface IUserManagerCreator {
    fun create(plugin: Chat): IUserManager
}