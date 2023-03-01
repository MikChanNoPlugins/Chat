package dev.mikchan.mcnp.chat

import dev.mikchan.mcnp.chat.interfaces.commands.ICommandManager
import dev.mikchan.mcnp.chat.interfaces.config.IConfig
import dev.mikchan.mcnp.chat.interfaces.events.IEventManager
import dev.mikchan.mcnp.chat.interfaces.formatting.IFormatter
import dev.mikchan.mcnp.chat.interfaces.keys.IKeys
import dev.mikchan.mcnp.chat.interfaces.users.IUserManager
import org.bstats.bukkit.Metrics
import org.bukkit.plugin.java.JavaPlugin

/**
 * The main plugin class.
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
class ChatPlugin : JavaPlugin() {
    companion object {
        private const val bStatsId = 15823
    }

    val factory = FactorySelector.selectFactory(this)

    val config: IConfig by lazy { factory.createConfig() }
    val commandManager: ICommandManager by lazy { factory.createCommandManager() }
    val formatter: IFormatter by lazy { factory.createFormatter() }
    val userManager: IUserManager by lazy { factory.createUserManager() }
    val eventManager: IEventManager by lazy { factory.createEventManager() }
    val keys: IKeys by lazy { factory.createKeys() }

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
