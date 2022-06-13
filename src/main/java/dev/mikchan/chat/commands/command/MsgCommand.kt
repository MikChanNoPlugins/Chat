package dev.mikchan.chat.commands.command

import dev.mikchan.chat.Chat
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

internal class MsgCommand(private val plugin: Chat, private val history: MutableMap<String, String>) : ICommand {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            sender.sendMessage(
                ChatColor.RED.toString() + "Invalid usage of the command %s. Please try again.".format(
                    label
                )
            )

            return false
        }

        val user = plugin.userManager.findByUsername(args[0])
        if (user == null) {
            sender.sendMessage(
                ChatColor.RED.toString() + "Player %s is not found. Please try again.".format(
                    args[0]
                )
            )

            return false
        }

        val message = args.drop(1).joinToString(" ").trim()
        if (message.isEmpty()) {
            return false
        }

        val formattedMessage = if (sender is Player) {
            plugin.formatter.formatPrivate(sender, user, message)
        } else {
            plugin.formatter.formatConsole(user, message)
        }

        user.sendMessage(formattedMessage)
        if (user != sender) {
            sender.sendMessage(formattedMessage)
        }

        val senderUUID = if (sender is Player) sender.uniqueId.toString() else "CONSOLE"
        val userUUID = user.uniqueId.toString()

        history[userUUID] = senderUUID

        return false
    }

    override fun onTabComplete(
        sender: CommandSender, command: Command, alias: String, args: Array<out String>
    ): MutableList<String>? {
        if (args.isEmpty()) return mutableListOf()

        if (args.size == 1) {
            return null
        }

        return mutableListOf()
    }
}