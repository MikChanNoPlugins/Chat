package dev.mikchan.mcnp.chat.contract.utility

import java.util.*

/**
 * A Utility interface
 */
interface IUtility {
    /**
     * Converts an [UUID] set to [ByteArray]
     *
     * @param uuids The set with UUIDs
     * @return The resulting [ByteArray]
     */
    fun uniqueIdSetToByteArray(uuids: Set<UUID>): ByteArray

    /**
     * Converts [ByteArray] to [UUID] set
     *
     * @param byteArray The [ByteArray]
     * @return The resulting set
     */
    fun byteArrayToUniqueIdSet(byteArray: ByteArray): Set<UUID>

    /**
     * Converts an [UUID] object to [ByteArray]
     *
     * @param uuid The [UUID] object
     * @return The resulting [ByteArray]
     */
    fun uniqueIdToByteArray(uuid: UUID): ByteArray

    /**
     * Converts a [ByteArray] to [UUID] object
     *
     * @param byteArray The [ByteArray]
     * @return The resulting [UUID] object. If the [byteArray] is malformed, returns `null`.
     */
    fun byteArrayToUniqueId(byteArray: ByteArray): UUID?
}
