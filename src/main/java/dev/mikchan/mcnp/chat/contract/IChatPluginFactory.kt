package dev.mikchan.mcnp.chat.contract

import dev.mikchan.mcnp.chat.contract.commands.ICommandManager
import dev.mikchan.mcnp.chat.contract.config.IConfig
import dev.mikchan.mcnp.chat.contract.events.IEventManager
import dev.mikchan.mcnp.chat.contract.formatting.IFormatter
import dev.mikchan.mcnp.chat.contract.keys.IKeys
import dev.mikchan.mcnp.chat.contract.log.IChatLogger
import dev.mikchan.mcnp.chat.contract.users.IUserManager

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

    fun createChatLogger(): IChatLogger
}
