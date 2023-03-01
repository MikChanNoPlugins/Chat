package dev.mikchan.mcnp.chat.interfaces

import dev.mikchan.mcnp.chat.interfaces.commands.ICommandManager
import dev.mikchan.mcnp.chat.interfaces.config.IConfig
import dev.mikchan.mcnp.chat.interfaces.events.IEventManager
import dev.mikchan.mcnp.chat.interfaces.formatting.IFormatter
import dev.mikchan.mcnp.chat.interfaces.keys.IKeys
import dev.mikchan.mcnp.chat.interfaces.users.IUserManager

/**
 * An abstract factory
 */
interface IChatPluginFactory {
    fun createConfig(): IConfig
    fun createCommandManager(): ICommandManager
    fun createEventManager(): IEventManager
    fun createFormatter(): IFormatter
    fun createKeys(): IKeys
    fun createUserManager(): IUserManager
}
