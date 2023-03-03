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
    /**
     * Creates a configuration
     *
     * @return A new configuration object
     */
    fun createConfig(): IConfig

    /**
     * Creates a command manager
     *
     * @return A new command manager
     */
    fun createCommandManager(): ICommandManager

    /**
     * Creates an event manager
     *
     * @return A new event manager
     */
    fun createEventManager(): IEventManager

    /**
     * Creates a formatter
     *
     * @return A new formatter
     */
    fun createFormatter(): IFormatter

    /**
     * Creates a key store
     *
     * @return A new key store
     */
    fun createKeys(): IKeys

    /**
     * Creates a user manager
     *
     * @return A new user manager
     */
    fun createUserManager(): IUserManager

    /**
     * Creates a chat logger
     *
     * @return A new chat logger
     */
    fun createChatLogger(): IChatLogger
}
