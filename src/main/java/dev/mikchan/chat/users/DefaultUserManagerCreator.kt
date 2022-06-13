package dev.mikchan.chat.users

import com.earth2me.essentials.Essentials
import dev.mikchan.chat.Chat

internal class DefaultUserManagerCreator : IUserManagerCreator {
    override fun create(plugin: Chat): IUserManager {
        val essentials = plugin.server.pluginManager.getPlugin("Essentials") as? Essentials

        if (essentials != null) {
            plugin.logger.info("Found Essentials. Hooking in...")

            return EssentialsUserManager(plugin, essentials)
        }

        return DefaultUserManager(plugin)
    }
}