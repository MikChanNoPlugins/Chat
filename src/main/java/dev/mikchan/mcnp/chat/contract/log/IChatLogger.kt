package dev.mikchan.mcnp.chat.contract.log

import org.bukkit.entity.Player

/**
 * A chat logger interface
 */
interface IChatLogger {
    /**
     * Logs global message
     *
     * @param player The player who sent the message
     * @param message The message
     */
    fun logGlobal(player: Player, message: String)

    /**
     * Logs local message
     *
     * @param player The player who sent the message
     * @param message The message
     */
    fun logLocal(player: Player, message: String)

    /**
     * Logs private message
     *
     * @param from The player who sent the message
     * @param to The player who receives the message
     * @param message The message
     */
    fun logPrivate(from: Player, to: Player, message: String)

    /**
     * Logs console private message
     *
     * @param player The player which is related to this log
     * @param fromConsole If `true` logs a `Console -> Player` type of message,
     *                    and if `false` logs `Player -> Console`
     * @param message The message
     */
    fun logConsole(player: Player, fromConsole: Boolean, message: String)
}
