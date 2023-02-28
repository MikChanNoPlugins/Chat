package dev.mikchan.mcnp.chat.keys

import org.bukkit.NamespacedKey
import org.bukkit.persistence.PersistentDataType

internal class Key<T, Z>(override val key: NamespacedKey, override val type: PersistentDataType<T, Z>) : IKey<T, Z>
