package dev.mikchan.chat.config

import dev.mikchan.chat.utility.IReloadable

interface IConfig {
    fun reload(): Boolean

    fun subscribe(entity: IReloadable)
    fun unsubscribe(entity: IReloadable)

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
