package dev.mikchan.mcnp.chat.contract.keys

import org.bukkit.NamespacedKey
import org.bukkit.persistence.PersistentDataType

/**
 * Key representation
 */
interface IKey<T> {
    /**
     * The key name
     */
    val key: NamespacedKey

    /**
     * The key type
     */
    val type: PersistentDataType<T, T>
}
