package dev.mikchan.mcnp.chat

import dev.mikchan.mcnp.chat.contract.IChatPluginFactory
import dev.mikchan.mcnp.chat.implementation.pre19spigot.Pre19SpigotChatPluginFactory
import dev.mikchan.mcnp.chat.implementation.spigot.latest.SpigotChatPluginFactory
import dev.mikchan.mcnp.chat.implementation.spigot.v1_16_5.SpigotV1m16p5ChatPluginFactory


internal object FactorySelector {
    @Suppress("unused")
    private class Version(val major: Int, val minor: Int, val patch: Int)

    private fun getVersion(plugin: ChatPlugin): Version? {
        val versionRegex = Regex("""(\d+)\.(\d+)(?:\.(\d+))?""")
        val match = versionRegex.find(plugin.server.bukkitVersion) ?: return null
        val (sMajor, sMinor, sPatch) = match.destructured

        val major = sMajor.toIntOrNull() ?: return null
        val minor = sMinor.toIntOrNull() ?: return null
        val patch = sPatch.toIntOrNull() ?: 0

        return Version(major, minor, patch)
    }

    fun selectFactory(plugin: ChatPlugin): IChatPluginFactory {
        val version = getVersion(plugin) ?: return SpigotChatPluginFactory(plugin)

        return when (version.major) {
            1 -> when (version.minor) {
                16 -> when (version.patch) {
                    5 -> SpigotV1m16p5ChatPluginFactory(plugin)
                    else -> SpigotV1m16p5ChatPluginFactory(plugin)
                }

                17 -> Pre19SpigotChatPluginFactory(plugin)
                18 -> Pre19SpigotChatPluginFactory(plugin)

                else -> SpigotChatPluginFactory(plugin)
            }

            // Minecraft 2?
            else -> SpigotChatPluginFactory(plugin)
        }
    }
}
