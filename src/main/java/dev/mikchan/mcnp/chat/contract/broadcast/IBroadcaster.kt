package dev.mikchan.mcnp.chat.contract.broadcast

import org.bukkit.entity.Player

/**
 * A broadcaster interface, primarily intended for plugin-bungee interaction
 */
interface IBroadcaster {
    /**
     * Enables the broadcaster
     */
    fun enable()

    /**
     * Disables the broadcaster
     */
    fun disable()

    /**
     * Broadcasts global message
     *
     * @param sender The player who sends the message
     * @param message Text of the message
     */
    fun broadcastGlobal(sender: Player, message: String)
}
