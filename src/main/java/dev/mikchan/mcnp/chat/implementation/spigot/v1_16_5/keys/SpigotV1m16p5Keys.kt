package dev.mikchan.mcnp.chat.implementation.spigot.v1_16_5.keys

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.contract.keys.IKeys
import org.bukkit.persistence.PersistentDataType

internal class SpigotV1m16p5Keys(plugin: ChatPlugin) : IKeys {
    override val spy = SpigotV1m16p5Key(plugin, "spy", PersistentDataType.BYTE)
    override val ignore = SpigotV1m16p5Key(plugin, "ignore", PersistentDataType.BYTE_ARRAY)
}
