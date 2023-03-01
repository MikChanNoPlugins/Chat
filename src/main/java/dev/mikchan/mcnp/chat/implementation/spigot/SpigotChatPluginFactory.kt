package dev.mikchan.mcnp.chat.implementation.spigot

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.implementation.fallback.config.FallbackConfig
import dev.mikchan.mcnp.chat.implementation.fallback.formatting.FallbackFormatter
import dev.mikchan.mcnp.chat.implementation.spigot.commands.SpigotCommandManager
import dev.mikchan.mcnp.chat.implementation.spigot.config.BoostedYamlConfig
import dev.mikchan.mcnp.chat.implementation.spigot.events.SpigotEventManager
import dev.mikchan.mcnp.chat.implementation.spigot.formatting.PAPIFormatter
import dev.mikchan.mcnp.chat.implementation.spigot.keys.SpigotKeys
import dev.mikchan.mcnp.chat.implementation.spigot.users.SpigotUserManager
import dev.mikchan.mcnp.chat.contract.IChatPluginFactory
import dev.mikchan.mcnp.chat.contract.commands.ICommandManager
import dev.mikchan.mcnp.chat.contract.config.IConfig
import dev.mikchan.mcnp.chat.contract.events.IEventManager
import dev.mikchan.mcnp.chat.contract.formatting.IFormatter
import dev.mikchan.mcnp.chat.contract.keys.IKeys
import dev.mikchan.mcnp.chat.contract.users.IUserManager
import me.clip.placeholderapi.PlaceholderAPIPlugin
import java.io.File

internal class SpigotChatPluginFactory(private val plugin: ChatPlugin) : IChatPluginFactory {
    override fun createConfig(): IConfig {
        val resource = plugin.getResource("config.yml")

        return if (resource != null) {
            BoostedYamlConfig(File(plugin.dataFolder, "config.yml"), resource)
        } else {
            FallbackConfig()
        }
    }

    override fun createCommandManager(): ICommandManager {
        return SpigotCommandManager(plugin)
    }

    override fun createEventManager(): IEventManager {
        return SpigotEventManager(plugin)
    }

    override fun createFormatter(): IFormatter {
        val papi = plugin.server.pluginManager.getPlugin("PlaceholderAPI") as? PlaceholderAPIPlugin

        if (papi != null) {
            plugin.logger.info("Found PlaceholderAPI. Hooking in...")

            return PAPIFormatter(plugin)
        }

        return FallbackFormatter(plugin)
    }

    override fun createKeys(): IKeys {
        return SpigotKeys(plugin)
    }

    override fun createUserManager(): IUserManager {
        return SpigotUserManager(plugin)
    }
}
