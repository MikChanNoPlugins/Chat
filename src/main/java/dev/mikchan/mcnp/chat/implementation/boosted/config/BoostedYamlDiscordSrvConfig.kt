package dev.mikchan.mcnp.chat.implementation.boosted.config

import dev.dejvokep.boostedyaml.YamlDocument
import dev.mikchan.mcnp.chat.contract.config.IDiscordSrvConfig

internal class BoostedYamlDiscordSrvConfig(private val config: YamlDocument) : IDiscordSrvConfig {
    override var enabled: Boolean
        get() = config.getBoolean("discordSRV.enabled", false)
        set(value) {
            config.set("discordSRV.enabled", value)
            config.save()
        }

    override var channelName: String
        get() = config.getString("discordSRV.channelName", "mcn.chat:global")
        set(value) {
            config.set("discordSRV.channelName", value)
            config.save()
        }
}
