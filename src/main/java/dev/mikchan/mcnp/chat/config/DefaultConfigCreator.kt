package dev.mikchan.mcnp.chat.config

import dev.mikchan.mcnp.chat.ChatPlugin
import java.io.File

internal class DefaultConfigCreator : IConfigCreator {
    override fun create(plugin: ChatPlugin): IConfig {
        val resource = plugin.getResource("config.yml")

        return if (resource != null) {
            BoostedYamlConfig(File(plugin.dataFolder, "config.yml"), resource)
        } else {
            plugin.logger.severe("Unable to load config.yml resource. Falling back to default values! (This should not happen, please, notify the developer!)")
            DefaultConfig()
        }
    }
}
