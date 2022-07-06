package dev.mikchan.mcnp.chat.formatting

import org.bukkit.entity.Player

interface IFormatter {
    fun formatPrivate(from: Player, to: Player, message: String): String
    fun formatConsole(to: Player, message: String): String
    fun formatGlobal(from: Player, message: String): String
    fun formatLocal(from: Player, message: String): String
}