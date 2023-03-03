package dev.mikchan.mcnp.chat.implementation.spigot.latest.keys

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.contract.keys.IKey
import org.bukkit.NamespacedKey
import org.bukkit.persistence.PersistentDataType

internal class SpigotKey<T>(
    plugin: ChatPlugin,
    key: String,
    override val type: PersistentDataType<T, T>
) : IKey<T> {
    override val key: NamespacedKey by lazy { NamespacedKey(plugin, key) }
}
