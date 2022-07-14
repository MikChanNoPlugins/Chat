package dev.mikchan.mcnp.chat.config

import dev.mikchan.mcnp.chat.ChatPlugin

/**
 * Config builder interface.
 */
interface IConfigCreator {
    /**
     * Builder method.
     *
     * @param plugin The main plugin object. Keep in mind, that at the moment when this method is called
     * [ChatPlugin.config], [ChatPlugin.commandManager], [ChatPlugin.formatter], [ChatPlugin.userManager],
     * and [ChatPlugin.eventManager] may not be initialized yet, and should be referenced neither by the method,
     * nor by the constructor of the returned object.
     *
     * @return A config object.
     */
    fun create(plugin: ChatPlugin): IConfig
}
