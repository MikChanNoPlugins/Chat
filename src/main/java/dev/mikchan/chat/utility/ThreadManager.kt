package dev.mikchan.chat.utility

import dev.mikchan.chat.Chat
import org.bukkit.Bukkit

internal class ThreadManager(private val plugin: Chat) {
    fun <T> getThreadSafe(caller: () -> T): T? {
        return try {
            if (Thread.currentThread().id == 1L) {
                caller()
            } else {
                Bukkit.getServer().scheduler.callSyncMethod(plugin, caller).get()
            }
        } catch (e: Exception) {
            plugin.logger.severe(e.stackTraceToString())
            null
        }
    }
}