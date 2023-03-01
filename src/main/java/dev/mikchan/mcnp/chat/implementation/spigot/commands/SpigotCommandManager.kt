package dev.mikchan.mcnp.chat.implementation.spigot.commands

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.implementation.spigot.commands.command.*
import dev.mikchan.mcnp.chat.interfaces.commands.ICommandManager

internal class SpigotCommandManager(private val plugin: ChatPlugin) : ICommandManager {
    private val messageHistory: MutableMap<String, String> = mutableMapOf()

    private val executors: Map<String, ICommand> = mapOf(
        "mcn_chat" to ReloadCommand(plugin),
        "msg" to MsgCommand(plugin, messageHistory),
        "reply" to ReplyCommand(plugin, messageHistory),
        "spy" to SpyCommand(plugin),
    )

    override fun enableAll() {
        disableAll()

        for (executor in executors) {
            val cmd = plugin.getCommand(executor.key) ?: continue

            cmd.setExecutor(executor.value)
            cmd.tabCompleter = executor.value
        }
    }

    override fun disableAll() {
        for (executor in executors) {
            val cmd = plugin.getCommand(executor.key) ?: continue

            cmd.setExecutor(null)
            cmd.tabCompleter = null
        }
    }
}
