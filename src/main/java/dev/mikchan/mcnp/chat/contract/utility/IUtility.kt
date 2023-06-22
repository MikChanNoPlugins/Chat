package dev.mikchan.mcnp.chat.contract.utility

import java.util.*

/**
 * A Utility interface
 */
interface IUtility {
    fun uniqueIdSetToByteArray(uuids: Set<UUID>): ByteArray
    fun byteArrayToUniqueIdSet(byteArray: ByteArray): Set<UUID>

    fun uniqueIdToByteArray(uuid: UUID): ByteArray
    fun byteArrayToUniqueId(byteArray: ByteArray): UUID?
}
