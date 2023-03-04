package dev.mikchan.mcnp.chat.implementation.base.commands

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.contract.commands.ICommandManager

internal typealias MessageHistory = MutableMap<String, String>
internal typealias CommandExecutors = Map<String, ICommand>

internal open class BaseCommandManager(
    private val plugin: ChatPlugin,
    commandsBuilder: (plugin: ChatPlugin, messageHistory: MessageHistory) -> CommandExecutors,
) : ICommandManager {
    private val messageHistory: MessageHistory = mutableMapOf()

    private val executors: CommandExecutors = commandsBuilder(plugin, messageHistory)

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
