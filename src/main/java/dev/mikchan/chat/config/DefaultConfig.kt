package dev.mikchan.chat.config


internal class DefaultConfig : ReloadableConfig() {
    override var enableLocal: Boolean
        get() = false
        set(_) {}

    override var localRadius: Int
        get() = 100
        set(_) {}

    override var globalPrefix: String
        get() = "!"
        set(_) {}

    override var playerTemplate: String
        get() = "%player_name%"
        set(_) {}

    override var fromTemplate: String
        get() = "%player_name%"
        set(_) {}

    override var toTemplate: String
        get() = "%player_name%"
        set(_) {}

    override var localTemplate: String
        get() = "<:player:> :message:"
        set(_) {}

    override var globalTemplate: String
        get() = "<:player:> :message:"
        set(_) {}

    override var privateTemplate: String
        get() = "<:player_from: to :player_to:> :message:"
        set(_) {}

    override var consoleTemplate: String
        get() = "<CONSOLE to :player_to:> :message:"
        set(_) {}
}
