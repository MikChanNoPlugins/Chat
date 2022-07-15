package dev.mikchan.mcnp.chat.commands

import dev.mikchan.mcnp.chat.ChatPlugin

/**
 * Command manager creator interface.
 */
interface ICommandManagerCreator {
    /**
     * Builder method.
     *
     * @param plugin The main plugin object. Keep in mind, that at the moment when this method is called
     * [ChatPlugin.commandManager], [ChatPlugin.formatter], [ChatPlugin.userManager], and [ChatPlugin.eventManager]
     * may not be initialized yet, and should be referenced neither by the method, nor by the constructor of the
     * returned object.
     *
     * @return A command manager object.
     */
    fun create(plugin: ChatPlugin): ICommandManager
}
