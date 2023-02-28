package dev.mikchan.mcnp.chat.formatting

import org.bukkit.entity.Player

/**
 * Formatter interface.
 */
interface IFormatter {
    /**
     * Formats a private message.
     *
     * @param from The player that sends the message.
     * @param to The player that receives the message.
     * @param message The text of the message.
     *
     * @return The formatted message.
     */
    fun formatPrivate(from: Player, to: Player, message: String): String

    /**
     * Formats a private message sent from the console.
     *
     * @param to The player that receives the message.
     * @param message The text of the message.
     *
     * @return The formatted message.
     */
    fun formatConsole(to: Player, message: String): String

    /**
     * Formats a global message.
     *
     * @param from The player that sent the message.
     * @param message The text of the message.
     *
     * @return The formatted message.
     */
    fun formatGlobal(from: Player, message: String): String

    /**
     * Formats a local message.
     *
     * @param from The player that sent the message.
     * @param message The text of the message.
     *
     * @return The formatted message.
     */
    fun formatLocal(from: Player, message: String): String

    /**
     * Formats a local message.
     *
     * @param from The player that sent the message.
     * @param message The text of the message.
     *
     * @return The formatted message.
     */
    fun formatSpy(from: Player, message: String): String
}
