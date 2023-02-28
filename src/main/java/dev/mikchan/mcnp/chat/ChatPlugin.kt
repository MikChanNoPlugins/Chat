package dev.mikchan.mcnp.chat

import dev.mikchan.mcnp.chat.commands.ICommandManager
import dev.mikchan.mcnp.chat.config.IConfig
import dev.mikchan.mcnp.chat.events.IEventManager
import dev.mikchan.mcnp.chat.formatting.IFormatter
import dev.mikchan.mcnp.chat.keys.IKeys
import dev.mikchan.mcnp.chat.users.IUserManager
import org.bstats.bukkit.Metrics
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

/**
 * The main plugin class.
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
class ChatPlugin : JavaPlugin() {
    companion object {
        private const val bStatsId = 15823
    }

    val config: IConfig = Creators.config.create(this)
    val commandManager: ICommandManager = Creators.commandManager.create(this)
    val formatter: IFormatter = Creators.formatter.create(this)
    val userManager: IUserManager = Creators.userManager.create(this)
    val eventManager: IEventManager = Creators.eventManager.create(this)
    val keys: IKeys = Creators.keys.create(this)

    val notSpigot = !server.version.contains("spigot", ignoreCase = true)

    override fun onEnable() {
        commandManager.enableAll()
        eventManager.register()

        Metrics(this, bStatsId)
    }

    override fun onDisable() {
        commandManager.disableAll()
        eventManager.unregister()
    }
}
