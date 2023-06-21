package dev.mikchan.mcnp.chat.implementation.fallback.formatting

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.implementation.base.formatting.BaseFormatter
import org.bukkit.ChatColor
import org.bukkit.entity.Player

internal class FallbackFormatter(private val plugin: ChatPlugin) : BaseFormatter(plugin) {
    private fun format(
        player: Player,
        template: String,
        message: String,
        alwaysTranslateColors: Boolean = false,
        to: (() -> String?)? = null
    ): String {
        val name = { player.displayName }
        return prepareTemplate(template, name, to ?: name, name, name, name, name) {
            if (alwaysTranslateColors || player.hasPermission("mcn.chat.colors")) {
                ChatColor.translateAlternateColorCodes(
                    '&', message
                )
            } else {
                ChatColor.stripColor(message)
            }
        }
    }

    override fun formatPrivate(from: Player, to: Player, message: String): String {
        return format(from, plugin.config.fromTemplate, message, to = {
            to.displayName
        })
    }

    override fun formatConsole(to: Player, message: String): String {
        return format(to, plugin.config.consoleTemplate, message, alwaysTranslateColors = true)
    }

    override fun formatToConsole(from: Player, message: String): String {
        return format(from, plugin.config.fromConsoleTemplate, message)
    }

    override fun formatGlobal(from: Player, message: String): String {
        return format(from, plugin.config.globalTemplate, message)
    }

    override fun formatLocal(from: Player, message: String): String {
        return format(from, plugin.config.localTemplate, message)
    }

    override fun formatSpy(from: Player, message: String): String {
        return format(from, plugin.config.spyTemplate, message)
    }

    override fun formatPlain(player: Player, message: String): String {
        return ChatColor.translateAlternateColorCodes('&', message)
    }
}
