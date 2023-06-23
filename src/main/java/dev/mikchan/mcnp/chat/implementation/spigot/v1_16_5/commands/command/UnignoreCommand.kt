package dev.mikchan.mcnp.chat.implementation.spigot.v1_16_5.commands.command

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.implementation.base.commands.BaseIgnoreCommand
import org.bukkit.entity.Player
import java.util.*

internal class UnignoreCommand(plugin: ChatPlugin) : BaseIgnoreCommand(plugin) {
    override fun processData(data: Set<UUID>, ignorePlayer: Player): Set<UUID> {
        return data - ignorePlayer.uniqueId
    }

    override val ignorePhrase: String = "is now not in the ignore list"
}
