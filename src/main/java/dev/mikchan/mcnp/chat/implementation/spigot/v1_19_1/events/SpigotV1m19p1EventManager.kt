package dev.mikchan.mcnp.chat.implementation.spigot.v1_19_1.events

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.implementation.base.event.BaseEventManager
import dev.mikchan.mcnp.chat.implementation.spigot.v1_16_5.events.listeners.SpigotV1m16p5DiscordChatListener
import dev.mikchan.mcnp.chat.implementation.spigot.v1_16_5.events.listeners.SpigotV1m16p5MCNCListener
import dev.mikchan.mcnp.chat.implementation.spigot.v1_19_1.events.listeners.SpigotV1m19p1ChatListener

internal class SpigotV1m19p1EventManager(chatPlugin: ChatPlugin) : BaseEventManager(
    chatPlugin,
    { plugin ->
        val result = mutableListOf(
            SpigotV1m19p1ChatListener(plugin),
            SpigotV1m16p5MCNCListener(plugin),
        )

        val discordSrv = chatPlugin.discordSrv
        if (discordSrv != null) {
            result.add(SpigotV1m16p5DiscordChatListener(discordSrv))
        }

        result
    },
)
