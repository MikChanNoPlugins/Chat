package dev.mikchan.mcnp.chat.formatting

import dev.mikchan.mcnp.chat.ChatPlugin
import org.bukkit.ChatColor

internal abstract class CommonFormatter(private val plugin: ChatPlugin) : IFormatter {
    protected fun prepareTemplate(
        template: String, from: () -> String?, to: () -> String?, player: () -> String?, message: () -> String?
    ): String {
        var res = ChatColor.translateAlternateColorCodes('&', template)

        if (res.contains(":player_from:")) {
            val fromPlayer = from() ?: ""
            res = res.replace(":player_from:", fromPlayer)
        }

        if (res.contains(":player_to:")) {
            val toPlayer = to() ?: ""
            res = res.replace(":player_to:", toPlayer)
        }

        if (res.contains(":player:")) {
            val defPlayer = player() ?: ""
            res = res.replace(":player:", defPlayer)
        }

        if (res.contains(":message:")) {
            val msg = message()

            res = if (msg == null) {
                plugin.logger.severe("Message appeared to be null. This should never happen, please, contact the developer.")
                res.replace(":message:", "")
            } else {
                res.replace(":message:", msg)
            }
        }

        return res
    }
}
