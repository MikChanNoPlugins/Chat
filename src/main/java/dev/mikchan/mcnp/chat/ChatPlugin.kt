package dev.mikchan.mcnp.chat

import dev.mikchan.mcnp.chat.contract.commands.ICommandManager
import dev.mikchan.mcnp.chat.contract.config.IConfig
import dev.mikchan.mcnp.chat.contract.events.IEventManager
import dev.mikchan.mcnp.chat.contract.formatting.IFormatter
import dev.mikchan.mcnp.chat.contract.keys.IKeys
import dev.mikchan.mcnp.chat.contract.log.IChatLogger
import dev.mikchan.mcnp.chat.contract.users.IUserManager
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
    val chatLogger: IChatLogger by lazy { factory.createChatLogger() }

    val isBungeeCord: Boolean by lazy {
        server.spigot().config.getConfigurationSection("settings")?.getBoolean("settings.bungeecord") ?: false
    }

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
