package dev.mikchan.mcnp.chat.keys

import org.bukkit.NamespacedKey
import org.bukkit.persistence.PersistentDataType

interface IKey<T, Z> {
    val key: NamespacedKey
    val type: PersistentDataType<T, Z>
}
