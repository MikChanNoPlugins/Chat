package dev.mikchan.mcnp.chat.implementation.spigot.v1_16_5.commands.command

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.implementation.base.commands.ICommand
import net.md_5.bungee.api.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender

internal class ReloadCommand(private val plugin: ChatPlugin) : ICommand {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.size != 1) return false
        if (!"reload".equals(args[0], true)) return false
        if (sender !is ConsoleCommandSender && !sender.hasPermission("mcn.chat.admin.reload")) {
            sender.sendMessage("${ChatColor.DARK_RED}You have no permission to do that.")
            return false
        }

        val res = plugin.config.reload()

        if (res) {
            sender.sendMessage("${ChatColor.DARK_GREEN}Successfully reloaded!")
        } else {
            sender.sendMessage("${ChatColor.DARK_RED}Failed to reload...")
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
