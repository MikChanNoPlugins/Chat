package dev.mikchan.mcnp.chat.users

import org.bukkit.entity.Player

interface IUserManager {
    fun findSimilar(search: String, playerContext: Player?): List<String>
    fun findByUsername(search: String): Player?
    fun findByUUID(search: String): Player?
}