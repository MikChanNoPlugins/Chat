package dev.mikchan.mcnp.chat.implementation.base.event.listeners

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.contract.events.MCNChatEvent
import org.bukkit.ChatColor
import org.bukkit.event.player.AsyncPlayerChatEvent

internal open class BaseChatListener(private val plugin: ChatPlugin) {
    private class NoPermissionException : Exception()
    private class EmptyMessageException : Exception()

    private fun convertGlobal(event: AsyncPlayerChatEvent, isPreview: Boolean, msg: String): MCNChatEvent {
        if (msg.isEmpty()) throw EmptyMessageException()
        if (!event.player.hasPermission("mcn.chat.global") && !isPreview) throw NoPermissionException()

        val recipients = plugin.server.onlinePlayers.toSet()
        val formattedMessage = plugin.formatter.formatGlobal(event.player, msg)

        return MCNChatEvent(event.player, recipients, true, msg, formattedMessage, isPreview, event.isAsynchronous)
    }

    private fun convertLocal(event: AsyncPlayerChatEvent, isPreview: Boolean, msg: String): MCNChatEvent {
        if (msg.isEmpty()) throw EmptyMessageException()
        if (!event.player.hasPermission("mcn.chat.local") && !isPreview) throw NoPermissionException()

        val localRadius = plugin.config.localRadius
        val recipients = plugin.server.onlinePlayers.toList().filter { player -> player.world == event.player.world }
            .filter { player -> player.location.distance(event.player.location) < localRadius }.toSet()

        val formattedMessage = plugin.formatter.formatLocal(event.player, msg)

        return MCNChatEvent(event.player, recipients, false, msg, formattedMessage, isPreview, event.isAsynchronous)
    }

    private fun convert(event: AsyncPlayerChatEvent, isPreview: Boolean): MCNChatEvent {
        val msg = event.message.trim()

        return if (!plugin.config.enableLocal) convertGlobal(event, isPreview, msg)
        else if (msg.startsWith(plugin.config.globalPrefix)) convertGlobal(
            event, isPreview, msg.drop(plugin.config.globalPrefix.length).trim()
        )
        else convertLocal(event, isPreview, msg)
    }

    protected fun handleChatEvent(event: AsyncPlayerChatEvent, isPreview: Boolean): Boolean {
        try {
            val mcncEvent = convert(event, isPreview)
            plugin.server.pluginManager.callEvent(mcncEvent)

            if (mcncEvent.isCancelled) {
                return false
            }

            // I have not a single idea if I do this correctly or not
            plugin.discordSrv?.processChatMessage(
                mcncEvent.sender,
                mcncEvent.message,
                if (mcncEvent.isGlobal) "global" else "local",
                mcncEvent.isCancelled,
                event
            )

            if (plugin.config.substituteEvents) {
                if (!isPreview) {
                    plugin.server.scheduler.scheduleSyncDelayedTask(plugin) {
                        plugin.server.consoleSender.sendMessage(mcncEvent.sender.uniqueId, mcncEvent.formattedMessage)
                        for (recipient in mcncEvent.recipients) {
                            recipient.sendMessage(mcncEvent.sender.uniqueId, mcncEvent.formattedMessage)
                        }
                    }
                }

                return false
            } else {
                event.format = mcncEvent.formattedMessage.replace("%", "%%")
                event.recipients.clear()
                event.recipients.addAll(mcncEvent.recipients)

                return true
            }
        } catch (ex: EmptyMessageException) {
            return false
        } catch (ex: NoPermissionException) {
            plugin.server.scheduler.scheduleSyncDelayedTask(plugin) {
                event.player.sendMessage("${ChatColor.RED}You have no permission to do that!")
            }

            return false
        }
    }
}
