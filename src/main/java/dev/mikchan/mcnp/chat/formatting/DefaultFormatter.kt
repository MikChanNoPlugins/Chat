package dev.mikchan.mcnp.chat.formatting

import dev.mikchan.mcnp.chat.ChatPlugin
import org.bukkit.ChatColor
import org.bukkit.entity.Player

internal class DefaultFormatter(private val plugin: ChatPlugin) : CommonFormatter(plugin) {
    override fun formatPrivate(from: Player, to: Player, message: String): String {
        return prepareTemplate(plugin.config.privateTemplate,
            { from.displayName },
            { to.displayName },
            { from.displayName },
            {
                if (from.hasPermission("mcn.chat.colors")) ChatColor.translateAlternateColorCodes(
                    '&', message
                ) else ChatColor.stripColor(message)
            })
    }

    override fun formatConsole(to: Player, message: String): String {
        return prepareTemplate(plugin.config.consoleTemplate, { null }, { to.displayName }, { to.displayName }, {
            ChatColor.translateAlternateColorCodes(
                '&', message
            )
        })
    }

    override fun formatGlobal(from: Player, message: String): String {
        return prepareTemplate(plugin.config.globalTemplate, { from.displayName }, { null }, { from.displayName }) {
            if (from.hasPermission("mcn.chat.colors")) ChatColor.translateAlternateColorCodes(
                '&', message
            ) else ChatColor.stripColor(message)
        }
    }

    override fun formatLocal(from: Player, message: String): String {
        return prepareTemplate(plugin.config.localTemplate, { from.displayName }, { null }, { from.displayName }, {
            if (from.hasPermission("mcn.chat.colors")) ChatColor.translateAlternateColorCodes(
                '&', message
            ) else ChatColor.stripColor(message)
        })
    }
}
