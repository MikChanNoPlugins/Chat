package dev.mikchan.mcnp.chat

import dev.mikchan.mcnp.chat.commands.DefaultCommandManagerCreator
import dev.mikchan.mcnp.chat.commands.ICommandManagerCreator
import dev.mikchan.mcnp.chat.config.DefaultConfigCreator
import dev.mikchan.mcnp.chat.config.IConfigCreator
import dev.mikchan.mcnp.chat.events.DefaultEventManagerCreator
import dev.mikchan.mcnp.chat.events.IEventManagerCreator
import dev.mikchan.mcnp.chat.formatting.DefaultFormatterCreator
import dev.mikchan.mcnp.chat.formatting.IFormatterCreator
import dev.mikchan.mcnp.chat.users.DefaultUserManagerCreator
import dev.mikchan.mcnp.chat.users.IUserManagerCreator

object Creators {
    var config: IConfigCreator = DefaultConfigCreator()
    var commandManager: ICommandManagerCreator = DefaultCommandManagerCreator()
    var formatter: IFormatterCreator = DefaultFormatterCreator()
    var userManager: IUserManagerCreator = DefaultUserManagerCreator()
    var eventManager: IEventManagerCreator = DefaultEventManagerCreator()
}
