package dev.mikchan.mcnp.chat.implementation.spigot.v1_16_5

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.contract.IChatPluginFactory
import dev.mikchan.mcnp.chat.contract.commands.ICommandManager
import dev.mikchan.mcnp.chat.contract.config.IConfig
import dev.mikchan.mcnp.chat.contract.events.IEventManager
import dev.mikchan.mcnp.chat.contract.formatting.IFormatter
import dev.mikchan.mcnp.chat.contract.keys.IKeys
import dev.mikchan.mcnp.chat.contract.log.IChatLogger
import dev.mikchan.mcnp.chat.contract.users.IUserManager
import dev.mikchan.mcnp.chat.implementation.boosted.config.BoostedYamlConfig
import dev.mikchan.mcnp.chat.implementation.fallback.config.FallbackConfig
import dev.mikchan.mcnp.chat.implementation.fallback.formatting.FallbackFormatter
import dev.mikchan.mcnp.chat.implementation.file.log.FileChatLogger
import dev.mikchan.mcnp.chat.implementation.papi.formatting.PAPIFormatter
import dev.mikchan.mcnp.chat.implementation.spigot.v1_16_5.commands.SpigotV1m16p5CommandManager
import dev.mikchan.mcnp.chat.implementation.spigot.v1_16_5.events.SpigotV1m16p5EventManager
import dev.mikchan.mcnp.chat.implementation.spigot.v1_16_5.keys.SpigotV1m16p5Keys
import dev.mikchan.mcnp.chat.implementation.spigot.v1_16_5.users.SpigotV1m16p5UserManager
import me.clip.placeholderapi.PlaceholderAPIPlugin
import java.io.File

internal open class SpigotV1m16p5ChatPluginFactory(private val plugin: ChatPlugin) : IChatPluginFactory {
    override fun createConfig(): IConfig {
        val resource = plugin.getResource("config.yml")

        return if (resource != null) {
            BoostedYamlConfig(File(plugin.dataFolder, "config.yml"), resource)
        } else {
            FallbackConfig()
        }
    }

    override fun createCommandManager(): ICommandManager {
        return SpigotV1m16p5CommandManager(plugin)
    }

    override fun createEventManager(): IEventManager {
        return SpigotV1m16p5EventManager(plugin)
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
        return SpigotV1m16p5Keys(plugin)
    }

    override fun createUserManager(): IUserManager {
        return SpigotV1m16p5UserManager(plugin)
    }

    override fun createChatLogger(): IChatLogger {
        return FileChatLogger(plugin)
    }
}
