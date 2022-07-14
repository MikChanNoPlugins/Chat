package dev.mikchan.mcnp.chat.formatting

import dev.mikchan.mcnp.chat.ChatPlugin

/**
 * Formatter creator interface.
 */
interface IFormatterCreator {
    /**
     * Builder method.
     *
     * @param plugin The main plugin object. Keep in mind, that at the moment when this method is called
     * [ChatPlugin.formatter], [ChatPlugin.userManager], and [ChatPlugin.eventManager] may not be initialized yet,
     * and should be referenced neither by the method, nor by the constructor of the returned object.
     *
     * @return A formatter object.
     */
    fun create(plugin: ChatPlugin): IFormatter
}
