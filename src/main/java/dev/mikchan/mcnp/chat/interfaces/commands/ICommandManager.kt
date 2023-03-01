package dev.mikchan.mcnp.chat.interfaces.commands

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
