package dev.mikchan.chat.users

import dev.mikchan.chat.Chat

interface IUserManagerCreator {
    fun create(plugin: Chat): IUserManager
}