package dev.mikchan.mcnp.chat.formatting

import dev.mikchan.mcnp.chat.ChatPlugin
import org.bukkit.ChatColor
import org.bukkit.entity.Player

internal class DefaultFormatter(private val plugin: ChatPlugin) : CommonFormatter(plugin) {
    override fun formatPrivate(from: Player, to: Player, message: String): String {
        return prepareTemplate(plugin.config.privateTemplate,
            { "%1\$s" },
            { to.displayName },
            { "%1\$s" },
            { "%1\$s" },
            { "%1\$s" },
            { "%1\$s" },
            { it.replace("%", "%%") },
            { null },
            {
                if (from.hasPermission("mcn.chat.colors")) ChatColor.translateAlternateColorCodes(
                    '&', message
                ) else ChatColor.stripColor(message)
            })
    }

    override fun formatConsole(to: Player, message: String): String {
        return prepareTemplate(plugin.config.consoleTemplate,
            { to.displayName },
            { to.displayName },
            { to.displayName },
            { to.displayName },
            { to.displayName },
            { to.displayName },
            { it.replace("%", "%%") },
            { null },
            {
                ChatColor.translateAlternateColorCodes(
                    '&', message
                )
            })
    }

    override fun formatGlobal(from: Player, message: String): String {
        return prepareTemplate(plugin.config.globalTemplate,
            { "%1\$s" },
            { "%1\$s" },
            { "%1\$s" },
            { "%1\$s" },
            { "%1\$s" },
            { "%1\$s" },
            { it.replace("%", "%%") },
            { null },
            {
                if (from.hasPermission("mcn.chat.colors")) ChatColor.translateAlternateColorCodes(
                    '&', message
                ) else ChatColor.stripColor(message)
            })
    }

    override fun formatLocal(from: Player, message: String): String {
        return prepareTemplate(plugin.config.localTemplate,
            { "%1\$s" },
            { "%1\$s" },
            { "%1\$s" },
            { "%1\$s" },
            { "%1\$s" },
            { "%1\$s" },
            { it.replace("%", "%%") },
            { null },
            {
                if (from.hasPermission("mcn.chat.colors")) ChatColor.translateAlternateColorCodes(
                    '&', message
                ) else ChatColor.stripColor(message)
            })
    }

    override fun formatSpy(from: Player, message: String): String {
        return prepareTemplate(plugin.config.spyTemplate,
            { "%1\$s" },
            { "%1\$s" },
            { "%1\$s" },
            { "%1\$s" },
            { "%1\$s" },
            { "%1\$s" },
            { it.replace("%", "%%") },
            { null },
            {
                if (from.hasPermission("mcn.chat.colors")) ChatColor.translateAlternateColorCodes(
                    '&', message
                ) else ChatColor.stripColor(message)
            })
    }
}
