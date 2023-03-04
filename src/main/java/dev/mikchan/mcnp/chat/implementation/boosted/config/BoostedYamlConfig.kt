package dev.mikchan.mcnp.chat.implementation.boosted.config

import dev.dejvokep.boostedyaml.YamlDocument
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings
import dev.dejvokep.boostedyaml.spigot.SpigotSerializer
import dev.mikchan.mcnp.chat.contract.config.IConfig
import java.io.File
import java.io.InputStream

internal class BoostedYamlConfig(document: File, resource: InputStream) : IConfig {
    private val config: YamlDocument = YamlDocument.create(
        document,
        resource,
        GeneralSettings.builder().setSerializer(SpigotSerializer.getInstance()).build(),
        LoaderSettings.builder().setAutoUpdate(true).build(),
        DumperSettings.DEFAULT,
        UpdaterSettings.builder().setVersioning(BasicVersioning("configVersion")).build()
    )

    override fun reload(): Boolean {
        return config.reload()
    }

    override var enableLog: Boolean
        get() = config.getBoolean("enableLog", false)
        set(value) {
            config.set("enableLog", value)
            config.save()
        }

    override var enableLocal: Boolean
        get() = config.getBoolean("enableLocal", false)
        set(value) {
            config.set("enableLocal", value)
            config.save()
        }

    override var localRadius: Int
        get() = config.getInt("localRadius", 100)
        set(value) {
            config.set("localRadius", value)
            config.save()
        }

    override var globalPrefix: String
        get() = config.getString("globalPrefix", "!").trim()
        set(value) {
            config.set("globalPrefix", value)
            config.save()
        }

    override var globalTemplate: String
        get() = config.getString("globalTemplate", "<:player:> :message:")
        set(value) {
            config.set("globalTemplate", value)
            config.save()
        }

    override var localTemplate: String
        get() = config.getString("localTemplate", "<:player:> :message:")
        set(value) {
            config.set("localTemplate", value)
            config.save()
        }

    override var spyTemplate: String
        get() = config.getString("spyTemplate", "<:player:> :message:")
        set(value) {
            config.set("spyTemplate", value)
            config.save()
        }

    override var privateTemplate: String
        get() = config.getString("privateTemplate", "<:player_from: to :player_to:> :message:")
        set(value) {
            config.set("privateTemplate", value)
            config.save()
        }

    override var consoleTemplate: String
        get() = config.getString("consoleTemplate", "<CONSOLE to :player_to:> :message:")
        set(value) {
            config.set("consoleTemplate", value)
            config.save()
        }

    override var playerTemplate: String
        get() = config.getString("playerTemplate", "%player_name%")
        set(value) {
            config.set("playerTemplate", value)
            config.save()
        }

    override var globalPlayerTemplate: String
        get() = config.getString("globalPlayerTemplate", "%player_name%")
        set(value) {
            config.set("globalPlayerTemplate", value)
            config.save()
        }

    override var localPlayerTemplate: String
        get() = config.getString("localPlayerTemplate", "%player_name%")
        set(value) {
            config.set("localPlayerTemplate", value)
            config.save()
        }

    override var spyPlayerTemplate: String
        get() = config.getString("spyPlayerTemplate", "%player_name%")
        set(value) {
            config.set("spyPlayerTemplate", value)
            config.save()
        }

    override var fromTemplate: String
        get() = config.getString("fromTemplate", "%player_name%")
        set(value) {
            config.set("fromTemplate", value)
            config.save()
        }

    override var toTemplate: String
        get() = config.getString("toTemplate", "%player_name%")
        set(value) {
            config.set("toTemplate", value)
            config.save()
        }

    override var substituteEvents: Boolean
        get() = config.getBoolean("substituteEvents", false)
        set(value) {
            config.set("substituteEvents", value)
            config.save()
        }
}
