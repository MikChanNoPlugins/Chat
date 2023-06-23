package dev.mikchan.mcnp.chat.contract.events

import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

/**
 * The plugins chat event.
 *
 * Intercepts [org.bukkit.event.player.AsyncPlayerChatEvent].
 */
@Suppress("unused")
class MCNChatEvent(
    /**
     * The player involved in the event.
     */
    val sender: Player,

    /**
     * Set of recipients that this chat message will be displayed to.
     */
    var recipients: Set<Player>,

    /**
     * Is this message globally visible.
     *
     * If [dev.mikchan.mcnp.chat.contract.config.IConfig.enableLocal] is `false`, this value is always `true`.
     */
    var isGlobal: Boolean,

    /**
     * The raw message that the player is attempting to send.
     */
    var message: String,

    /**
     * Fully formatted message.
     */
    var formattedMessage: String,

    /**
     * Is the message meant to be previewed in the secure chat thing (meh)
     */
    val isPreview: Boolean,

    /**
     * Is the message async
     */
    isAsync: Boolean
) : Event(isAsync), Cancellable {
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
