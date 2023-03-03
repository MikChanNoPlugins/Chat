package dev.mikchan.mcnp.chat.implementation.spigot.latest.users

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.contract.users.IUserManager
import org.bukkit.entity.Player
import java.util.*

internal open class SpigotUserManager(private val plugin: ChatPlugin) : IUserManager {
    override fun findSimilar(search: String, playerContext: Player?): List<String> {
        @Suppress("UnnecessaryVariable") val res =
            plugin.server.onlinePlayers.filter { player -> player.displayName.startsWith(search, true) }
                .map { player -> player.displayName }.toList()

        // TODO: Weird feature. Is it even needed?
        /*
        if (res.isEmpty()) {
            res = plugin.server.onlinePlayers.stream()
                .filter { player -> player.uniqueId.toString().startsWith(search, true) }
                .map { player -> player.uniqueId.toString() }.toList()
        }
        */

        return res
    }

    override fun findByUsername(search: String): Player? {
        return plugin.server.getPlayer(search)
    }

    override fun findByUUID(search: String): Player? {
        return try {
            plugin.server.getPlayer(UUID.fromString(search))
        } catch (_: Exception) {
            null
        }
    }
}
