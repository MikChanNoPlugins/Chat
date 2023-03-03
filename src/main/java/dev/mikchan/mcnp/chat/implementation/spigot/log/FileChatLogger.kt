package dev.mikchan.mcnp.chat.implementation.spigot.log

import dev.mikchan.mcnp.chat.ChatPlugin
import dev.mikchan.mcnp.chat.contract.log.IChatLogger
import org.bukkit.entity.Player
import java.io.File
import java.io.FileOutputStream
import java.io.PrintStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread
import kotlin.concurrent.withLock
import kotlin.random.Random

internal class FileChatLogger(val plugin: ChatPlugin) : IChatLogger {
    private val mutex = ReentrantLock()
    private val folder = File(plugin.dataFolder.path, "logs")
    private var lastFileName: String? = null
    private var stream: PrintStream? = null

    private val printer: PrintStream
        get() {
            val now = SimpleDateFormat("yyyy-MM-dd").format(Date())

            val stream = stream
            if (lastFileName == now && stream != null) {
                return stream
            }

            val newFileName = "${now}.log"
            lastFileName = now

            folder.mkdirs()
            val newFile = File(folder, newFileName)
            val newStream = PrintStream(FileOutputStream(newFile, true))

            this.stream?.close()
            this.stream = newStream

            return newStream
        }

    private fun getTime(): String {
        return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).format(Date())
    }

    private fun write(log: String) {
        if (!plugin.config.enableLog) return

        thread(name = "mcn-chat-log-${Random.nextInt()}") {
            mutex.withLock {
                printer.println("[${getTime()}]$log")
            }
        }
    }

    override fun logGlobal(player: Player, message: String) {
        write("[GLOBAL] ${player.name}: $message (at ${player.location})")
    }

    override fun logLocal(player: Player, message: String) {
        write("[LOCAL] ${player.name}: $message (at ${player.location})")
    }

    override fun logPrivate(from: Player, to: Player, message: String) {
        write("[PRIVATE] ${from.name} to ${to.name}: $message (at ${from.location})")
    }

    override fun logConsole(player: Player, fromConsole: Boolean, message: String) {
        write(
            if (fromConsole) {
                "[PRIVATE] CONSOLE, ${player.name}: $message"
            } else {
                "[PRIVATE] ${player.name}, CONSOLE: $message (at ${player.location})"
            }
        )
    }
}
