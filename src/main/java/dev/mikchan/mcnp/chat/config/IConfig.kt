package dev.mikchan.mcnp.chat.config

interface IConfig {
    fun reload(): Boolean

    var enableLocal: Boolean
    var localRadius: Int

    var globalPrefix: String

    var playerTemplate: String
    var fromTemplate: String
    var toTemplate: String

    var localTemplate: String
    var globalTemplate: String
    var privateTemplate: String
    var consoleTemplate: String
}
