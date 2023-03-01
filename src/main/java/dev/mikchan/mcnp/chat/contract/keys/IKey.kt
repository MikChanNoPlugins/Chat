package dev.mikchan.mcnp.chat.contract.keys

import org.bukkit.NamespacedKey
import org.bukkit.persistence.PersistentDataType

interface IKey<T> {
    val key: NamespacedKey
    val type: PersistentDataType<T, T>
}
