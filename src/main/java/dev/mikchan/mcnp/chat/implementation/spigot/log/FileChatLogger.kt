package dev.mikchan.mcnp.chat.implementation.spigot.log

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.contract.log.IChatLogger
import org.bukkit.entity.Player
import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread
import kotlin.concurrent.withLock

internal class FileChatLogger(val plugin: ChatPlugin) : IChatLogger {
    private val mutex = ReentrantLock()
    private val folder = File(plugin.dataFolder, "logs")
    private var lastFileName: String? = null
    private var writer: BufferedWriter? = FileOutputStream(folder, true).bufferedWriter()

    private fun getWriter(): BufferedWriter {
        val now = SimpleDateFormat("yyyy-MM-dd").format(Date())

        val writer = writer
        if (lastFileName == now && writer != null) {
            return writer
        }

        val newFileName = "${now}.log"
        val newFile = File(folder, newFileName)
        val newWriter = FileOutputStream(newFile, true).bufferedWriter()

        lastFileName = now
        this.writer = newWriter

        return newWriter
    }

    private fun getTime(): String {
        return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).format(Date())
    }

    private fun write(log: String) {
        if (!plugin.config.enableLog) return

        thread {
            mutex.withLock {
                getWriter().use {
                    it.write("[${getTime()}] ")
                    it.write(log)
                    it.newLine()
                }
            }
        }
    }

    override fun logGlobal(player: Player, message: String) {
        write("[GLOBAL] (${player.uniqueId}) ${player.name}: $message")
    }

    override fun logLocal(player: Player, message: String) {
        write("[LOCAL] (${player.uniqueId}) ${player.name}: $message")
    }

    override fun logPrivate(from: Player, to: Player, message: String) {
        write("[PRIVATE] (${from.uniqueId}, ${to.uniqueId}) ${from.name}, ${to.name}: $message")
    }

    override fun logConsole(player: Player, fromConsole: Boolean, message: String) {
        write(
            if (fromConsole) {
                "[PRIVATE] (${player.uniqueId}) CONSOLE, ${player.name}: $message"
            } else {
                "[PRIVATE] (${player.uniqueId}) ${player.name}, CONSOLE: $message"
            }
        )
    }
}
