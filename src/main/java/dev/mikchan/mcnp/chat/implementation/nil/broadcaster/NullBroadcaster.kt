package dev.mikchan.mcnp.chat.implementation.nil.broadcaster

import dev.mikchan.mcnp.chat.contract.broadcast.IBroadcaster
import org.bukkit.entity.Player

internal class NullBroadcaster : IBroadcaster {
    override fun enable() {}

    override fun disable() {}

    override fun broadcastGlobal(sender: Player, message: String) {}
}
