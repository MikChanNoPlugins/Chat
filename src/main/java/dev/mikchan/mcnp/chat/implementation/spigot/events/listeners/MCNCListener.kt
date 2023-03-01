package dev.mikchan.mcnp.chat.implementation.spigot.events.listeners

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.contract.events.MCNChatEvent
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener

internal class MCNCListener(private val plugin: ChatPlugin) : Listener {
    private fun checkSpy(player: Player): Boolean {
        return try {
            player.persistentDataContainer.getOrDefault(
                plugin.keys.spy.key, plugin.keys.spy.type, 0.toByte()
            ) != 0.toByte() && player.hasPermission("mcn.chat.spy")
        } catch (ignore: Exception) {
            false
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    fun onMCNCEvent(event: MCNChatEvent) {
        if (event.isCancelled) return
        if (event.isPreview) return
        if (event.isGlobal) return

        plugin.server.scheduler.scheduleSyncDelayedTask(plugin) {
            val spies = plugin.server.onlinePlayers.filter { player -> !event.recipients.contains(player) }
                .filter { player -> checkSpy(player) }.toSet()

            if (spies.isEmpty()) return@scheduleSyncDelayedTask

            val msg = plugin.formatter.formatSpy(event.sender, event.message)
            for (spy in spies) {
                spy.sendMessage(event.sender.uniqueId, msg)
            }
        }
    }
}
