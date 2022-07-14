package dev.mikchan.mcnp.chat.users

import dev.mikchan.mcnp.chat.ChatPlugin

interface IUserManagerCreator {
    fun create(plugin: ChatPlugin): IUserManager
}