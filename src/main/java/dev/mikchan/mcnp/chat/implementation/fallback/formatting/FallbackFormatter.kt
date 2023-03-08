package dev.mikchan.mcnp.chat.implementation.fallback.formatting

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.implementation.base.formatting.BaseFormatter
import org.bukkit.ChatColor
import org.bukkit.entity.Player

internal class FallbackFormatter(private val plugin: ChatPlugin) : BaseFormatter(plugin) {
    private fun formatOnePlayer(
        player: Player,
        playerName: String,
        template: String,
        message: String,
        alwaysTranslateColors: Boolean = false,
    ): String {
        return prepareTemplate(template,
            { playerName },
            { playerName },
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
        return prepareTemplate(plugin.config.privateTemplate,
            { "%1\$s" },
            { to.displayName },
            { "%1\$s" },
            { "%1\$s" },
            { "%1\$s" },
            { "%1\$s" },
            {
                if (from.hasPermission("mcn.chat.colors")) {
                    ChatColor.translateAlternateColorCodes(
                        '&', message
                    )
                } else {
                    ChatColor.stripColor(message)
                }
            })
    }

    override fun formatConsole(to: Player, message: String): String {
        return formatOnePlayer(to, to.displayName, plugin.config.consoleTemplate, message, false)
    }

    override fun formatToConsole(from: Player, message: String): String {
        return formatOnePlayer(from, "%1\$s", plugin.config.fromConsoleTemplate, message)
    }

    override fun formatGlobal(from: Player, message: String): String {
        return formatOnePlayer(from, "%1\$s", plugin.config.globalTemplate, message)
    }

    override fun formatLocal(from: Player, message: String): String {
        return formatOnePlayer(from, "%1\$s", plugin.config.localTemplate, message)
    }

    override fun formatSpy(from: Player, message: String): String {
        return formatOnePlayer(from, "%1\$s", plugin.config.spyTemplate, message)
    }
}
