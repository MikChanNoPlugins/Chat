package dev.mikchan.chat.users

import com.earth2me.essentials.Essentials
import dev.mikchan.chat.Chat
import org.bukkit.entity.Player

internal class EssentialsUserManager(plugin: Chat, private val essentials: Essentials) : DefaultUserManager(plugin) {
    override fun findSimilar(search: String, playerContext: Player?): List<String> {
        if (playerContext == null) {
            return super.findSimilar(search, null)
        }

        val players = essentials.onlineUsers
        val res = mutableListOf<String>()

        for (player in players) {
            if (!player.displayName.startsWith(search, true)) continue
            if (player.isHidden(playerContext)) continue
            res.add(player.displayName)
        }

        return res
    }
}