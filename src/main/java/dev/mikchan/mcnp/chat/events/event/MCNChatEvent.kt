package dev.mikchan.mcnp.chat.events.event

import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

@Suppress("unused")
class MCNChatEvent(
    val sender: Player, val recipients: Set<Player>, val isGlobal: Boolean, val message: String,
    val formattedMessage: String
) : Event(), Cancellable {
    private var cancelled = false

    override fun isCancelled(): Boolean {
        return cancelled
    }

    override fun setCancelled(cancel: Boolean) {
        cancelled = cancel
    }

    override fun getHandlers(): HandlerList {
        return pHandlerList
    }

    companion object {
        private val pHandlerList = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return pHandlerList
        }
    }
}