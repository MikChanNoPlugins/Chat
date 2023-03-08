package dev.mikchan.mcnp.chat.implementation.papi.formatting

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.implementation.base.formatting.BaseFormatter
import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.ChatColor
import org.bukkit.entity.Player

internal class PAPIFormatter(private val plugin: ChatPlugin) : BaseFormatter(plugin) {
    private fun prepareFromPlayer(player: Player): String {
        return ChatColor.translateAlternateColorCodes(
            '&', PlaceholderAPI.setPlaceholders(player, plugin.config.fromTemplate)
        )
    }

    private fun prepareToPlayer(player: Player): String {
        return ChatColor.translateAlternateColorCodes(
            '&', PlaceholderAPI.setPlaceholders(player, plugin.config.toTemplate)
        )
    }

    private fun preparePlayer(player: Player): String {
        return ChatColor.translateAlternateColorCodes(
            '&', PlaceholderAPI.setPlaceholders(player, plugin.config.playerTemplate)
        )
    }

    private fun prepareGlobalPlayer(player: Player): String {
        return ChatColor.translateAlternateColorCodes(
            '&', PlaceholderAPI.setPlaceholders(player, plugin.config.globalPlayerTemplate)
        )
    }

    private fun prepareLocalPlayer(player: Player): String {
        return ChatColor.translateAlternateColorCodes(
            '&', PlaceholderAPI.setPlaceholders(player, plugin.config.localPlayerTemplate)
        )
    }

    private fun prepareSpyPlayer(player: Player): String {
        return ChatColor.translateAlternateColorCodes(
            '&', PlaceholderAPI.setPlaceholders(player, plugin.config.spyPlayerTemplate)
        )
    }

    private fun formatOnePlayer(player: Player, template: String, message: String): String {
        return prepareTemplate(template,
            { prepareFromPlayer(player) },
            { prepareToPlayer(player) },
            { preparePlayer(player) },
            { prepareGlobalPlayer(player) },
            { prepareLocalPlayer(player) },
            { prepareSpyPlayer(player) },
            {
                if (player.hasPermission("mcn.chat.colors")) ChatColor.translateAlternateColorCodes(
                    '&', message
                ) else ChatColor.stripColor(message)
            })
    }

    override fun formatPrivate(from: Player, to: Player, message: String): String {
        return prepareTemplate(plugin.config.privateTemplate,
            { prepareFromPlayer(from) },
            { prepareToPlayer(to) },
            { preparePlayer(from) },
            { prepareGlobalPlayer(from) },
            { prepareLocalPlayer(from) },
            { prepareSpyPlayer(from) },
            {
                if (from.hasPermission("mcn.chat.colors")) ChatColor.translateAlternateColorCodes(
                    '&', message
                ) else ChatColor.stripColor(message)
            })
    }

    override fun formatConsole(to: Player, message: String): String {
        return prepareTemplate(plugin.config.consoleTemplate,
            { prepareFromPlayer(to) },
            { prepareToPlayer(to) },
            { preparePlayer(to) },
            { prepareGlobalPlayer(to) },
            { prepareLocalPlayer(to) },
            { prepareSpyPlayer(to) },
            {
                ChatColor.translateAlternateColorCodes(
                    '&', message
                )
            })
    }

    override fun formatToConsole(from: Player, message: String): String {
        return formatOnePlayer(from, plugin.config.consoleTemplate, message)
    }

    override fun formatGlobal(from: Player, message: String): String {
        return formatOnePlayer(from, plugin.config.globalTemplate, message)
    }

    override fun formatLocal(from: Player, message: String): String {
        return formatOnePlayer(from, plugin.config.localTemplate, message)
    }

    override fun formatSpy(from: Player, message: String): String {
        return formatOnePlayer(from, plugin.config.spyTemplate, message)
    }
}
