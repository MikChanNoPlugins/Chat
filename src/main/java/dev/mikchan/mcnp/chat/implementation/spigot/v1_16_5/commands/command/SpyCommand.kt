package dev.mikchan.mcnp.chat.implementation.spigot.v1_16_5.commands.command

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.implementation.base.commands.ICommand
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SpyCommand(private val plugin: ChatPlugin) : ICommand {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val player = sender as? Player

        if (player == null) {
            sender.sendMessage("${ChatColor.DARK_RED}This action is only accessible to players.")
            return false
        }

        if (!player.hasPermission("mcn.chat.spy")) {
            sender.sendMessage("${ChatColor.DARK_RED}You have no permission to do that.")
            return false
        }

        val prev = try {
            player.persistentDataContainer.getOrDefault(plugin.keys.spy.key, plugin.keys.spy.type, 0.toByte())
        } catch (ignore: Exception) {
            0.toByte()
        }

        val next = ((prev + 1) % 2).toByte()
        player.persistentDataContainer.set(plugin.keys.spy.key, plugin.keys.spy.type, next)

        when (next) {
            1.toByte() -> {
                player.sendMessage("${ChatColor.DARK_GREEN}Spy is switched on!")
            }

            0.toByte() -> {
                player.sendMessage("${ChatColor.DARK_GREEN}Spy is switched off!")
            }

            else -> {
                player.sendMessage("${ChatColor.DARK_RED}Error.")
            }
        }

        return true
    }

    override fun onTabComplete(
        sender: CommandSender, command: Command, label: String, args: Array<out String>
    ): MutableList<String> {
        return mutableListOf()
    }
}
