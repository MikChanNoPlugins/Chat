package dev.mikchan.mcnp.chat.contract.log

import org.bukkit.entity.Player

interface IChatLogger {
    fun logGlobal(player: Player, message: String)
    fun logLocal(player: Player, message: String)
    fun logPrivate(from: Player, to: Player, message: String)
    fun logConsole(player: Player, fromConsole: Boolean, message: String)
}
