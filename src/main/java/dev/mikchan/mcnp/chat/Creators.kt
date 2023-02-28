package dev.mikchan.mcnp.chat

import dev.mikchan.mcnp.chat.commands.DefaultCommandManagerCreator
import dev.mikchan.mcnp.chat.commands.ICommandManagerCreator
import dev.mikchan.mcnp.chat.config.DefaultConfigCreator
import dev.mikchan.mcnp.chat.config.IConfigCreator
import dev.mikchan.mcnp.chat.events.DefaultEventManagerCreator
import dev.mikchan.mcnp.chat.events.IEventManagerCreator
import dev.mikchan.mcnp.chat.formatting.DefaultFormatterCreator
import dev.mikchan.mcnp.chat.formatting.IFormatterCreator
import dev.mikchan.mcnp.chat.keys.DefaultKeysCreator
import dev.mikchan.mcnp.chat.keys.IKeysCreator
import dev.mikchan.mcnp.chat.users.DefaultUserManagerCreator
import dev.mikchan.mcnp.chat.users.IUserManagerCreator

/**
 * The object with all necessary builders.
 *
 * This object contains all necessary builders for [ChatPlugin] methods.
 * It is possible to change the behavior of [ChatPlugin] by overriding these
 * builders.
 */
object Creators {
    /**
     * The config builder.
     */
    var config: IConfigCreator = DefaultConfigCreator()

    /**
     * The command manager builder.
     */
    var commandManager: ICommandManagerCreator = DefaultCommandManagerCreator()

    /**
     * The formatter builder.
     */
    var formatter: IFormatterCreator = DefaultFormatterCreator()

    /**
     * The user manager builder.
     */
    var userManager: IUserManagerCreator = DefaultUserManagerCreator()

    /**
     * The event manager builder.
     */
    var eventManager: IEventManagerCreator = DefaultEventManagerCreator()

    /**
     * The keys' builder.
     */
    var keys: IKeysCreator = DefaultKeysCreator()
}
