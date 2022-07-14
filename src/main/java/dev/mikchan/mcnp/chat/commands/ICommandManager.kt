package dev.mikchan.mcnp.chat.commands

/**
 * The command manager interface.
 *
 * Manages plugin commands.
 */
interface ICommandManager {
    /**
     * Enables all commands.
     */
    fun enableAll()

    /**
     * Disables all commands.
     */
    fun disableAll()
}
