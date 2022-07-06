package dev.mikchan.mcnp.chat.commands

import dev.mikchan.mcnp.chat.Chat
import dev.mikchan.mcnp.chat.commands.command.ICommand
import dev.mikchan.mcnp.chat.commands.command.MsgCommand
import dev.mikchan.mcnp.chat.commands.command.ReloadCommand
import dev.mikchan.mcnp.chat.commands.command.ReplyCommand

internal class DefaultCommandManager(private val plugin: Chat) : ICommandManager {
    private val messageHistory: MutableMap<String, String> = mutableMapOf()

    private val executors: Map<String, ICommand> = mapOf(
        "mcnc_reload" to ReloadCommand(plugin),
        "msg" to MsgCommand(plugin, messageHistory),
        "reply" to ReplyCommand(plugin, messageHistory)
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
