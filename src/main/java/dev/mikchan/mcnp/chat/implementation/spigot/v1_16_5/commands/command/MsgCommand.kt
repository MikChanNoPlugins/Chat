package dev.mikchan.mcnp.chat.implementation.spigot.v1_16_5.commands.command

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.implementation.base.commands.ICommand
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

internal class MsgCommand(private val plugin: ChatPlugin, private val history: MutableMap<String, String>) : ICommand {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            sender.sendMessage(
                "${ChatColor.DARK_RED}Invalid usage of the command $label. Please try again."
            )

            return false
        }

        val user = plugin.userManager.findByUsername(args[0])
        if (user == null) {
            sender.sendMessage(
                "${ChatColor.DARK_RED}Player ${args[0]} is not found. Please try again."
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

        if (sender is Player) {
            if (!plugin.userManager.doesIgnore(user, sender)) user.sendMessage(sender.uniqueId, formattedMessage)
            if (user != sender) sender.sendMessage(sender.uniqueId, formattedMessage)

            plugin.chatLogger.logPrivate(sender, user, message)
        } else {
            user.sendMessage(formattedMessage)
            if (user != sender) sender.sendMessage(formattedMessage)

            plugin.chatLogger.logConsole(user, true, message)
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
