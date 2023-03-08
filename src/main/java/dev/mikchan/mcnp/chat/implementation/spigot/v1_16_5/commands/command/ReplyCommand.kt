package dev.mikchan.mcnp.chat.implementation.spigot.v1_16_5.commands.command

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.implementation.base.commands.ICommand
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

internal class ReplyCommand(private val plugin: ChatPlugin, private val history: MutableMap<String, String>) :
    ICommand {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val msg = args.joinToString(" ").trim()
        if (msg.isEmpty()) {
            return false
        }

        val senderUUID = if (sender is Player) sender.uniqueId.toString() else "CONSOLE"

        val recipientUUID = history[senderUUID]
        val recipient = if (recipientUUID == "CONSOLE") {
            plugin.server.consoleSender
        } else if (recipientUUID != null) {
            plugin.userManager.findByUUID(recipientUUID)
        } else {
            null
        }

        if (recipient == null) {
            sender.sendMessage(ChatColor.RED.toString() + "Nobody to reply to.")
            return false
        }

        val formattedMessage = if (recipient is Player && sender is Player) {
            plugin.formatter.formatPrivate(sender, recipient, msg)
        } else if (recipient is Player && sender !is Player) {
            plugin.formatter.formatConsole(recipient, msg)
        } else if (sender is Player) {
            plugin.formatter.formatToConsole(sender, msg)
        } else {
            return false
        }

        if (recipient is Player) {
            sender.sendMessage(recipient.uniqueId, formattedMessage)
        } else {
            sender.sendMessage(formattedMessage)
        }

        if (recipient != sender) {
            if (sender is Player) {
                recipient.sendMessage(sender.uniqueId, formattedMessage)
            } else {
                recipient.sendMessage(formattedMessage)
            }
        }

        if (sender is Player && recipient is Player) {
            plugin.chatLogger.logPrivate(sender, recipient, msg)
        } else if (sender is Player && recipient !is Player) {
            plugin.chatLogger.logConsole(sender, false, msg)
        } else if (recipient is Player) {
            plugin.chatLogger.logConsole(recipient, true, msg)
        }

        if (recipientUUID != null) {
            history[recipientUUID] = senderUUID
        }

        return true
    }

    override fun onTabComplete(
        sender: CommandSender, command: Command, alias: String, args: Array<out String>
    ): MutableList<String> {
        return mutableListOf()
    }
}
