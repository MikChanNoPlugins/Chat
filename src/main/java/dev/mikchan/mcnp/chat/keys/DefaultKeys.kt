package dev.mikchan.mcnp.chat.keys

import dev.mikchan.mcnp.chat.ChatPlugin
import org.bukkit.NamespacedKey
import org.bukkit.persistence.PersistentDataType

internal class DefaultKeys(plugin: ChatPlugin) : IKeys {
    override val spy = Key(NamespacedKey(plugin, "spy"), PersistentDataType.BYTE)
}
