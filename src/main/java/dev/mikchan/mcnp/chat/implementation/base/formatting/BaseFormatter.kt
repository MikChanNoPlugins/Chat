package dev.mikchan.mcnp.chat.implementation.base.formatting

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.interfaces.formatting.IFormatter
import org.bukkit.ChatColor

internal abstract class BaseFormatter(private val plugin: ChatPlugin) : IFormatter {
    protected fun prepareTemplate(
        template: String,
        from: () -> String?,
        to: () -> String?,
        player: () -> String?,
        globalPlayer: () -> String?,
        localPlayer: () -> String?,
        spyPlayer: () -> String?,
        preProcess: (s: String) -> String?,
        postProcess: (s: String) -> String?,
        message: () -> String?
    ): String {
        var res = template

        res = preProcess(res) ?: res
        res = ChatColor.translateAlternateColorCodes('&', res)

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

        if (res.contains(":global_player:")) {
            val defPlayer = globalPlayer() ?: ""
            res = res.replace(":global_player:", defPlayer)
        }

        if (res.contains(":local_player:")) {
            val defPlayer = localPlayer() ?: ""
            res = res.replace(":local_player:", defPlayer)
        }

        if (res.contains(":spy_player:")) {
            val defPlayer = spyPlayer() ?: ""
            res = res.replace(":spy_player:", defPlayer)
        }

        res = postProcess(res) ?: res

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
