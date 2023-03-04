package dev.mikchan.mcnp.chat.implementation.nil.broadcaster

import dev.mikchan.mcnp.chat.contract.broadcast.IBroadcaster

internal class NullBroadcaster : IBroadcaster {
    override fun enable() {}

    override fun disable() {}
}
