package dev.mikchan.mcnp.chat.implementation.spigot.v1_16_5.commands

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.implementation.base.commands.BaseCommandManager
import dev.mikchan.mcnp.chat.implementation.spigot.v1_16_5.commands.command.*

internal class SpigotV1m16p5CommandManager(chatPlugin: ChatPlugin) : BaseCommandManager(
    chatPlugin,
    { plugin, messageHistory ->
        mapOf(
            "mcn_chat" to ReloadCommand(plugin),
            "msg" to MsgCommand(plugin, messageHistory),
            "reply" to ReplyCommand(plugin, messageHistory),
            "spy" to SpyCommand(plugin),
            "ignore" to IgnoreCommand(plugin),
            "unignore" to UnignoreCommand(plugin),
        )
    },
)
