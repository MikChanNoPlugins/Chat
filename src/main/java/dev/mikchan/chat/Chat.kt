package dev.mikchan.chat

import dev.mikchan.chat.commands.ICommandManager
import dev.mikchan.chat.config.IConfig
import dev.mikchan.chat.events.IEventManager
import dev.mikchan.chat.formatting.IFormatter
import dev.mikchan.chat.users.IUserManager
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused", "MemberVisibilityCanBePrivate")
class Chat : JavaPlugin() {
    val config: IConfig = Creators.config.create(this)
    val commandManager: ICommandManager = Creators.commandManager.create(this)
    val formatter: IFormatter = Creators.formatter.create(this)
    val userManager: IUserManager = Creators.userManager.create(this)
    val eventManager: IEventManager = Creators.eventManager.create(this)

    override fun onEnable() {
        commandManager.enableAll()
        eventManager.register()
    }

    override fun onDisable() {
        commandManager.disableAll()
        eventManager.unregister()
    }
}