package dev.mikchan.mcnp.chat.users

import org.bukkit.entity.Player

/**
 * User manager interface
 */
interface IUserManager {
    /**
     * A method to find all online player names that start with [search]
     *
     * @param search The search parameter, the beginning of the username.
     * @param playerContext A player in whose context search is done.
     *
     * @return All usernames of players, who are online and visible to [playerContext] (if not `null`).
     */
    fun findSimilar(search: String, playerContext: Player?): List<String>

    /**
     * Finds an online player based on the username.
     *
     * @param search The username of a player that should be found.
     *
     * @return The player object, if found. `null` if not found or not online.
     */
    fun findByUsername(search: String): Player?

    /**
     * Finds an online player based on uuid.
     *
     * @param search An uuid in [String] format.
     *
     * @return The player object, if found. `null` if player is not found, not online, or [search] is not a valid UUID.
     */
    fun findByUUID(search: String): Player?
}
