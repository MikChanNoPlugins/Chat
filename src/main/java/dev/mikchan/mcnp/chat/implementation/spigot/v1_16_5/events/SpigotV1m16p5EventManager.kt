package dev.mikchan.mcnp.chat.implementation.spigot.v1_16_5.events

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.implementation.base.event.BaseEventManager
import dev.mikchan.mcnp.chat.implementation.spigot.v1_16_5.events.listeners.SpigotV1m16p5ChatListener
import dev.mikchan.mcnp.chat.implementation.spigot.v1_16_5.events.listeners.SpigotV1m16p5DiscordChatListener
import dev.mikchan.mcnp.chat.implementation.spigot.v1_16_5.events.listeners.SpigotV1m16p5MCNCListener

internal class SpigotV1m16p5EventManager(chatPlugin: ChatPlugin) : BaseEventManager(
    chatPlugin,
    { plugin ->
        val result = mutableListOf(
            SpigotV1m16p5ChatListener(plugin),
            SpigotV1m16p5MCNCListener(plugin),
        )

        val discordSrv = chatPlugin.discordSrv
        if (discordSrv != null) {
            result.add(SpigotV1m16p5DiscordChatListener(discordSrv))
        }

        result
    },
)
