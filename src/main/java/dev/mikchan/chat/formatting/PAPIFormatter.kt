package dev.mikchan.chat.formatting

import dev.mikchan.chat.Chat
import dev.mikchan.chat.utility.IReloadable
import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.ChatColor
import org.bukkit.entity.Player

internal class PAPIFormatter(private val plugin: Chat) : CommonFormatter(plugin), IReloadable {
    private fun prepareFromPlayer(player: Player): String {
        return PlaceholderAPI.setPlaceholders(player, plugin.config.fromTemplate)
    }

    private fun prepareToPlayer(player: Player): String {
        return PlaceholderAPI.setPlaceholders(player, plugin.config.toTemplate)
    }

    private fun preparePlayer(player: Player): String {
        return PlaceholderAPI.setPlaceholders(player, plugin.config.playerTemplate)
    }

    override fun formatPrivate(from: Player, to: Player, message: String): String {
        return prepareTemplate(plugin.config.privateTemplate,
            { prepareFromPlayer(from) },
            { prepareToPlayer(to) },
            { preparePlayer(to) },
            { if (from.hasPermission("mcn.chat.colors")) ChatColor.stripColor(message) else message })
    }

    override fun formatConsole(to: Player, message: String): String {
        return prepareTemplate(plugin.config.consoleTemplate,
            { null },
            { prepareToPlayer(to) },
            { preparePlayer(to) },
            { message })
    }

    override fun formatGlobal(from: Player, message: String): String {
        return prepareTemplate(plugin.config.globalTemplate,
            { prepareFromPlayer(from) },
            { null },
            { preparePlayer(from) },
            { if (from.hasPermission("mcn.chat.colors")) ChatColor.stripColor(message) else message })
    }

    override fun formatLocal(from: Player, message: String): String {
        return prepareTemplate(plugin.config.localTemplate,
            { prepareFromPlayer(from) },
            { null },
            { preparePlayer(from) },
            { if (from.hasPermission("mcn.chat.colors")) ChatColor.stripColor(message) else message })
    }

    override fun reload() {}
}