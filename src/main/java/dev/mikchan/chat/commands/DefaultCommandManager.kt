package dev.mikchan.chat.commands

import dev.mikchan.chat.Chat
import dev.mikchan.chat.commands.command.ICommand
import dev.mikchan.chat.commands.command.MsgCommand
import dev.mikchan.chat.commands.command.ReloadCommand
import dev.mikchan.chat.commands.command.ReplyCommand

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
