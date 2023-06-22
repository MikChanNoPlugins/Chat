package dev.mikchan.mcnp.chat.implementation.spigot.v1_16_5.utility

import dev.mikchan.mcnp.chat.contract.utility.IUtility
import java.nio.ByteBuffer
import java.util.*

internal class SpigotV1m16p5Utility : IUtility {
    override fun uniqueIdSetToByteArray(uuids: Set<UUID>): ByteArray {
        return uuids.flatMap { uniqueIdToByteArray(it).toList() }.toByteArray()
    }

    override fun byteArrayToUniqueIdSet(byteArray: ByteArray): Set<UUID> {
        return byteArray.toList().chunked(16).mapNotNull { byteArrayToUniqueId(it.toByteArray()) }.toSet()
    }

    override fun uniqueIdToByteArray(uuid: UUID): ByteArray {
        val buffer = ByteBuffer.wrap(ByteArray(16))
        buffer.putLong(uuid.mostSignificantBits)
        buffer.putLong(uuid.leastSignificantBits)
        return buffer.array()
    }

    override fun byteArrayToUniqueId(byteArray: ByteArray): UUID? {
        if (byteArray.size != 16) return null
        val buffer = ByteBuffer.wrap(byteArray)
        val high = buffer.getLong()
        val low = buffer.getLong()
        return UUID(high, low)
    }
}
