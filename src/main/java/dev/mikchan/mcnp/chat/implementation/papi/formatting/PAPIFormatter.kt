package dev.mikchan.mcnp.chat.implementation.papi.formatting

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.implementation.base.formatting.BaseFormatter
import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.ChatColor
import org.bukkit.entity.Player

internal class PAPIFormatter(private val plugin: ChatPlugin) : BaseFormatter(plugin) {
    private fun prepareColors(player: Player, template: String): String {
        return ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, template))
    }

    private fun format(
        player: Player,
        template: String,
        message: String,
        alwaysTranslateColors: Boolean = false,
        to: (() -> String?)? = null,
    ): String {
        return prepareTemplate(template,
            { prepareColors(player, plugin.config.fromTemplate) },
            to ?: { prepareColors(player, plugin.config.toTemplate) },
            { prepareColors(player, plugin.config.playerTemplate) },
            { prepareColors(player, plugin.config.globalPlayerTemplate) },
            { prepareColors(player, plugin.config.localPlayerTemplate) },
            { prepareColors(player, plugin.config.spyPlayerTemplate) },
            {
                if (alwaysTranslateColors || player.hasPermission("mcn.chat.colors")) {
                    ChatColor.translateAlternateColorCodes(
                        '&', message
                    )
                } else {
                    ChatColor.stripColor(message)
                }
            })
    }

    override fun formatPrivate(from: Player, to: Player, message: String): String {
        return format(from, plugin.config.privateTemplate, message, to = {
            prepareColors(to, plugin.config.toTemplate)
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
}
