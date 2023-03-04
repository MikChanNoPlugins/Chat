package dev.mikchan.mcnp.chat.contract.broadcast

/**
 * A broadcaster interface, primarily intended for plugin-bungee interaction
 */
interface IBroadcaster {
    /**
     * Enables the broadcaster
     */
    fun enable()

    /**
     * Disables the broadcaster
     */
    fun disable()
}
