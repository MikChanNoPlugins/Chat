package dev.mikchan.mcnp.chat.implementation.spigot.v1_16_5.commands.command

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.implementation.base.commands.ICommand
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

internal class IgnoreCommand(private val plugin: ChatPlugin) : ICommand {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val player = sender as? Player

        if (player == null) {
            sender.sendMessage("${ChatColor.DARK_RED}This action is only accessible to players.")
            return false
        }

        if (!player.hasPermission("mcn.chat.ignore")) {
            sender.sendMessage("${ChatColor.DARK_RED}You have no permission to do that.")
            return false
        }

        val ignorePlayerName = args.getOrNull(0)

        if (ignorePlayerName == null) {
            sender.sendMessage("${ChatColor.DARK_RED}The player name is not specified")
            return false
        }

        val ignorePlayer = plugin.userManager.findByUsername(ignorePlayerName)

        if (ignorePlayer == null) {
            sender.sendMessage("${ChatColor.DARK_RED}The player '${ignorePlayerName}' is not found.")
            return false
        }

        if (ignorePlayer == player) {
            sender.sendMessage("${ChatColor.DARK_RED}You can't ignore yourself!")
            return false
        }

        val bytes = player.persistentDataContainer.get(plugin.keys.ignore.key, plugin.keys.ignore.type) ?: ByteArray(0)
        val data = plugin.utility.byteArrayToUniqueIdSet(bytes).toMutableSet()
        data += ignorePlayer.uniqueId
        player.persistentDataContainer.set(
            plugin.keys.ignore.key,
            plugin.keys.ignore.type,
            plugin.utility.uniqueIdSetToByteArray(data)
        )

        sender.sendMessage("${ChatColor.DARK_GREEN}The player '$ignorePlayerName' is now in the ignore list!")

        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): MutableList<String>? {
        if (args.size == 1) return null
        return mutableListOf()
    }
}
