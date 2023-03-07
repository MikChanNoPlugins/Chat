package dev.mikchan.mcnp.chat.implementation.spigot.v1_16_5.broadcaster

import com.google.common.io.ByteStreams
import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.contract.broadcast.IBroadcaster
import org.bukkit.entity.Player
import org.bukkit.plugin.messaging.PluginMessageListener

internal class SpigotV1m16p5Broadcaster(private val plugin: ChatPlugin) : IBroadcaster, PluginMessageListener {
    companion object {
        private const val bungeeChannel = "BungeeCord"
    }

    override fun enable() {
        plugin.server.messenger.registerOutgoingPluginChannel(plugin, bungeeChannel)
        plugin.server.messenger.registerIncomingPluginChannel(plugin, bungeeChannel, this)
    }

    override fun disable() {
        plugin.server.messenger.unregisterOutgoingPluginChannel(plugin, bungeeChannel)
        plugin.server.messenger.unregisterIncomingPluginChannel(plugin, bungeeChannel)
    }

    override fun broadcastGlobal(sender: Player, message: String) {
        val formattedMsg = plugin.formatter.formatGlobal(sender, message)

        val out = ByteStreams.newDataOutput()

        out.writeUTF("Message")
        out.writeUTF("ALL")
        out.writeUTF(formattedMsg)
    }

    override fun onPluginMessageReceived(channel: String, player: Player, message: ByteArray) {
        if (channel != bungeeChannel) return
    }
}
