package dev.mikchan.mcnp.chat.users

import dev.mikchan.mcnp.chat.ChatPlugin

/**
 * User manager creator interface.
 */
interface IUserManagerCreator {
    /**
     * Builder method.
     *
     * @param plugin The main plugin object. Keep in mind, that at the moment when this method is called
     * [ChatPlugin.userManager] and [ChatPlugin.eventManager] may not be initialized yet,
     * and should be referenced neither by the method, nor by the constructor of the returned object.
     *
     * @return A user manager object.
     */
    fun create(plugin: ChatPlugin): IUserManager
}
