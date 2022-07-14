package dev.mikchan.mcnp.chat.formatting

import dev.mikchan.mcnp.chat.ChatPlugin
import me.clip.placeholderapi.PlaceholderAPIPlugin

internal class DefaultFormatterCreator : IFormatterCreator {
    override fun create(plugin: ChatPlugin): IFormatter {
        val papi = plugin.server.pluginManager.getPlugin("PlaceholderAPI") as? PlaceholderAPIPlugin

        if (papi != null) {
            plugin.logger.info("Found PlaceholderAPI. Hooking in...")

            return PAPIFormatter(plugin)
        }

        return DefaultFormatter(plugin)
    }
}