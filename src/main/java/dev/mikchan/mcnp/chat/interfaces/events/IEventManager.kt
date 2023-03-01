package dev.mikchan.mcnp.chat.interfaces.events

/**
 * Event manager interface.
 *
 * Manages all plugin event listeners.
 */
interface IEventManager {
    /**
     * Unregister all events listeners.
     */
    fun unregister()

    /**
     * Register all event listeners.
     */
    fun register()
}
