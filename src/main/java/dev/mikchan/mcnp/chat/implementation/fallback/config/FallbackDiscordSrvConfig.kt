package dev.mikchan.mcnp.chat.implementation.fallback.config

import dev.mikchan.mcnp.chat.contract.config.IDiscordSrvConfig

internal class FallbackDiscordSrvConfig : IDiscordSrvConfig {
    override var enabled: Boolean
        get() = false
        set(_) {}

    override var channelName: String
        get() = "mcn.chat:global"
        set(_) {}
}
