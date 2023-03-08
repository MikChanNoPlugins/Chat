package dev.mikchan.mcnp.chat.implementation.fallback.formatting

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.implementation.base.formatting.BaseFormatter
import org.bukkit.ChatColor
import org.bukkit.entity.Player

internal class FallbackFormatter(private val plugin: ChatPlugin) : BaseFormatter(plugin) {
    private fun format(
        player: Player,
        playerName: String,
        template: String,
        message: String,
        alwaysTranslateColors: Boolean = false,
        to: (() -> String?)? = null
    ): String {
        return prepareTemplate(template,
            { playerName },
            to ?: { playerName },
            { playerName },
            { playerName },
            { playerName },
            { playerName },
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
        return format(from, "%1\$s", plugin.config.fromConsoleTemplate, message, to = {
            to.displayName
        })
    }

    override fun formatConsole(to: Player, message: String): String {
        return format(to, to.displayName, plugin.config.consoleTemplate, message, alwaysTranslateColors = true)
    }

    override fun formatToConsole(from: Player, message: String): String {
        return format(from, "%1\$s", plugin.config.fromConsoleTemplate, message)
    }

    override fun formatGlobal(from: Player, message: String): String {
        return format(from, "%1\$s", plugin.config.globalTemplate, message)
    }

    override fun formatLocal(from: Player, message: String): String {
        return format(from, "%1\$s", plugin.config.localTemplate, message)
    }

    override fun formatSpy(from: Player, message: String): String {
        return format(from, "%1\$s", plugin.config.spyTemplate, message)
    }
}
