package dev.mikchan.mcnp.chat.interfaces.keys

import org.bukkit.NamespacedKey
import org.bukkit.persistence.PersistentDataType

interface IKey<T> {
    val key: NamespacedKey
    val type: PersistentDataType<T, T>
}
