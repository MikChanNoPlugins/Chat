package dev.mikchan.mcnp.chat.implementation.spigot.keys

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.contract.keys.IKeys
import org.bukkit.persistence.PersistentDataType

internal class SpigotKeys(plugin: ChatPlugin) : IKeys {
    override val spy = SpigotKey(plugin, "spy", PersistentDataType.BYTE)
}
