package dev.mikchan.mcnp.chat.contract.keys

/**
 * Namespaced keys collection interface
 */
interface IKeys {
    /**
     * Namespaced key related to spy feature
     */
    val spy: IKey<Byte>

    /**
     * Namespaced key related to ignore feature
     */
    val ignore: IKey<ByteArray>
}
