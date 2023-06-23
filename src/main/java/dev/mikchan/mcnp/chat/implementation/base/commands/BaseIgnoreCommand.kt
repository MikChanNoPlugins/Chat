package dev.mikchan.mcnp.chat.implementation.base.commands

import dev.mikchan.mcnp.chat.ChatPlugin
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

internal abstract class BaseIgnoreCommand(private val plugin: ChatPlugin) : ICommand {
    protected abstract fun processData(data: Set<UUID>, ignorePlayer: Player): Set<UUID>
    protected abstract val ignorePhrase: String

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
        val data = processData(plugin.utility.byteArrayToUniqueIdSet(bytes), ignorePlayer)
        player.persistentDataContainer.set(
            plugin.keys.ignore.key,
            plugin.keys.ignore.type,
            plugin.utility.uniqueIdSetToByteArray(data)
        )

        sender.sendMessage("${ChatColor.DARK_GREEN}The player '$ignorePlayerName' $ignorePhrase!")

        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): MutableList<String>? {
        return if (args.size == 1) null else mutableListOf()
    }
}
