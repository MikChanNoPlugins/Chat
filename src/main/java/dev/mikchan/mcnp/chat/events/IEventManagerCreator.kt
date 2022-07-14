package dev.mikchan.mcnp.chat.events

import dev.mikchan.mcnp.chat.ChatPlugin

/**
 * Event manager creator interface.
 */
interface IEventManagerCreator {
    /**
     * Builder method.
     *
     * @param plugin The main plugin object. Keep in mind, that at the moment when this method is called
     * [ChatPlugin.eventManager] may not be initialized yet, and should be referenced neither by the method,
     * nor by the constructor of the returned object.
     *
     * @return An event manager object.
     */
    fun create(plugin: ChatPlugin): IEventManager
}
