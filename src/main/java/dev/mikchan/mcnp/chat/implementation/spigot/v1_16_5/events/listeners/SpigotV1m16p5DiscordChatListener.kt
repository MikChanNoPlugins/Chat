package dev.mikchan.mcnp.chat.implementation.spigot.v1_16_5.events.listeners

import dev.mikchan.mcnp.chat.contract.events.MCNChatEvent
import github.scarsz.discordsrv.DiscordSRV
import github.scarsz.discordsrv.util.DiscordUtil
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

internal class SpigotV1m16p5DiscordChatListener(private val discordSRV: DiscordSRV) : Listener {
    @EventHandler
    fun onDiscordHandle(event: MCNChatEvent) {
        if (event.isPreview) return
        if (!event.isGlobal) return

        discordSRV.getOptionalTextChannel("mcn.chat:global")?.let { textChannel ->
            DiscordUtil.sendMessage(textChannel, ChatColor.stripColor(event.formattedMessage))
        }
    }
}
