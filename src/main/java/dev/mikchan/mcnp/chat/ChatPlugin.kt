package dev.mikchan.mcnp.chat

import dev.mikchan.mcnp.chat.commands.ICommandManager
import dev.mikchan.mcnp.chat.config.IConfig
import dev.mikchan.mcnp.chat.events.IEventManager
import dev.mikchan.mcnp.chat.formatting.IFormatter
import dev.mikchan.mcnp.chat.users.IUserManager
import org.bukkit.plugin.java.JavaPlugin

/**
 * The main plugin class.
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
class ChatPlugin : JavaPlugin() {
    val config: IConfig = dev.mikchan.mcnp.chat.Creators.config.create(this)
    val commandManager: ICommandManager = dev.mikchan.mcnp.chat.Creators.commandManager.create(this)
    val formatter: IFormatter = dev.mikchan.mcnp.chat.Creators.formatter.create(this)
    val userManager: IUserManager = dev.mikchan.mcnp.chat.Creators.userManager.create(this)
    val eventManager: IEventManager = dev.mikchan.mcnp.chat.Creators.eventManager.create(this)

    override fun onEnable() {
        commandManager.enableAll()
        eventManager.register()
    }

    override fun onDisable() {
        commandManager.disableAll()
        eventManager.unregister()
    }
}
