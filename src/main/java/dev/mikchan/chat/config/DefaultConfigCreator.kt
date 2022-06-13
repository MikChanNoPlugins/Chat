package dev.mikchan.chat.config

import dev.mikchan.chat.Chat
import java.io.File

internal class DefaultConfigCreator : IConfigCreator {
    override fun create(plugin: Chat): IConfig {
        val resource = plugin.getResource("config.yml")

        return if (resource != null) {
            BoostedYamlConfig(File(plugin.dataFolder, "config.yml"), resource)
        } else {
            plugin.logger.severe("Unable to load config.yml resource. Falling back to default values! (This should not happen, please, notify the developer!)")
            DefaultConfig()
        }
    }
}
