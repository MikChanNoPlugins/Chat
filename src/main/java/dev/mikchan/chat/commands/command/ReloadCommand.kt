package dev.mikchan.chat.commands.command

import dev.mikchan.chat.Chat
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

internal class ReloadCommand(private val plugin: Chat) : ICommand {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val res = plugin.config.reload()

        if (res) {
            sender.sendMessage(ChatColor.GREEN.toString() + "Successfully reloaded!")
        } else {
            sender.sendMessage(ChatColor.RED.toString() + "Failed to reload...")
            return false
        }

        return true
    }

    override fun onTabComplete(
        sender: CommandSender, command: Command, alias: String, args: Array<out String>
    ): MutableList<String> {
        return mutableListOf()
    }
}