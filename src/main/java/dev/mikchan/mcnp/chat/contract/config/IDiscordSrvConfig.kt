package dev.mikchan.mcnp.chat.contract.config

/**
 * DiscordSRV configuration
 */
interface IDiscordSrvConfig {
    /**
     * Is DiscordSrv integration enabled
     */
    var enabled: Boolean

    /**
     * The channel for messages to listen to
     */
    var channelName: String
}
