package dev.mikchan.chat

import dev.mikchan.chat.commands.DefaultCommandManagerCreator
import dev.mikchan.chat.commands.ICommandManagerCreator
import dev.mikchan.chat.config.DefaultConfigCreator
import dev.mikchan.chat.config.IConfigCreator
import dev.mikchan.chat.events.DefaultEventManagerCreator
import dev.mikchan.chat.events.IEventManagerCreator
import dev.mikchan.chat.formatting.DefaultFormatterCreator
import dev.mikchan.chat.formatting.IFormatterCreator
import dev.mikchan.chat.users.DefaultUserManagerCreator
import dev.mikchan.chat.users.IUserManagerCreator

object Creators {
    var config: IConfigCreator = DefaultConfigCreator()
    var commandManager: ICommandManagerCreator = DefaultCommandManagerCreator()
    var formatter: IFormatterCreator = DefaultFormatterCreator()
    var userManager: IUserManagerCreator = DefaultUserManagerCreator()
    var eventManager: IEventManagerCreator = DefaultEventManagerCreator()
}
