package dev.mikchan.mcnp.chat

import net.md_5.bungee.api.plugin.Plugin

@Suppress("unused", "MemberVisibilityCanBePrivate")
class BungeeChatPlugin : Plugin() {
    override fun onEnable() {
        logger.info("Yeah!!")
    }

    override fun onDisable() {
        logger.info("Oh no!")
    }
}
