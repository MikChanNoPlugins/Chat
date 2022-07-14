package dev.mikchan.mcnp.chat.config

/**
 * The config interface.
 */
interface IConfig {
    /**
     * Reload method.
     *
     * @return `true` if the config was reloaded successfully, `false` if not.
     */
    fun reload(): Boolean

    /**
     * Is local chat enabled.
     *
     * If enabled and the message the player sent does not start with [globalPrefix], this message will only be visible by players within [localRadius].
     */
    var enableLocal: Boolean

    /**
     * The radius of the local chat.
     *
     * If [enableLocal] is `true`, all messages sent by a player that do not start with [globalPrefix] will only by visible by players within this radius.
     */
    var localRadius: Int

    /**
     * The global prefix.
     *
     * If [enableLocal] is `true`, this is the prefix that should be used for all messages that should be visible globally.
     * If [enableLocal] is `false`, the value is not important.
     */
    var globalPrefix: String

    /**
     * The player name template.
     *
     * This is the template which is used to replace `:player:` part in [localTemplate] and [globalTemplate].
     */
    var playerTemplate: String

    /**
     * Sending player name template.
     *
     * This is the template which is used to replace `:player_from:` part in [privateTemplate].
     */
    var fromTemplate: String

    /**
     * Receiving player name template.
     *
     * This is the template which is used to replace `:player_to:` part in [privateTemplate] and [consoleTemplate].
     */
    var toTemplate: String

    /**
     * The local message template.
     *
     * This is the template which is used for local messages. If [enableLocal] is `false` the value is ignored.
     * Any `:player:` will be replaced with [playerTemplate], and `:message:` with the message text.
     */
    var localTemplate: String

    /**
     * The global message template.
     *
     * This is the template which is used for global messages.
     * Any `:player:` will be replaced with [playerTemplate], and `:message:` with the message text.
     */
    var globalTemplate: String

    /**
     * The private message template.
     *
     * This is the template which is used for private messages.
     * Any `:player_from:` will be replaced with [fromTemplate], `:player_to:` with [toTemplate], and `:message:` with the message text.
     */
    var privateTemplate: String

    /**
     * The console private message template.
     *
     * This is the template which is used for private messages that were sent from the console.
     * Any `:player_to` will be replaced with [toTemplate], and `:message:` with the message text.
     */
    var consoleTemplate: String
}
