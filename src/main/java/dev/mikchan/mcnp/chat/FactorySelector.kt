package dev.mikchan.mcnp.chat

import dev.mikchan.mcnp.chat.contract.IChatPluginFactory
import dev.mikchan.mcnp.chat.implementation.spigot.latest.SpigotLatestChatPluginFactory
import dev.mikchan.mcnp.chat.implementation.spigot.v1_16_5.SpigotV1m16p5ChatPluginFactory
import dev.mikchan.mcnp.chat.implementation.spigot.v1_17.SpigotV1m17ChatPluginFactory
import dev.mikchan.mcnp.chat.implementation.spigot.v1_17_1.SpigotV1m17p1ChatPluginFactory
import dev.mikchan.mcnp.chat.implementation.spigot.v1_18.SpigotV1m18ChatPluginFactory
import dev.mikchan.mcnp.chat.implementation.spigot.v1_18_1.SpigotV1m18p1ChatPluginFactory
import dev.mikchan.mcnp.chat.implementation.spigot.v1_18_2.SpigotV1m18p2ChatPluginFactory
import dev.mikchan.mcnp.chat.implementation.spigot.v1_19.SpigotV1m19ChatPluginFactory
import dev.mikchan.mcnp.chat.implementation.spigot.v1_19_1.SpigotV1m19p1ChatPluginFactory
import dev.mikchan.mcnp.chat.implementation.spigot.v1_19_2.SpigotV1m19p2ChatPluginFactory
import dev.mikchan.mcnp.chat.implementation.spigot.v1_19_3.SpigotV1m19p3ChatPluginFactory


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
        val version = getVersion(plugin) ?: return SpigotLatestChatPluginFactory(plugin)

        return when (version.major) {
            1 -> when (version.minor) {
                16 -> when (version.patch) {
                    5 -> SpigotV1m16p5ChatPluginFactory(plugin)
                    else -> SpigotV1m16p5ChatPluginFactory(plugin)
                }

                17 -> when (version.patch) {
                    0 -> SpigotV1m17ChatPluginFactory(plugin)
                    1 -> SpigotV1m17p1ChatPluginFactory(plugin)
                    else -> SpigotV1m17p1ChatPluginFactory(plugin)
                }

                18 -> when (version.patch) {
                    0 -> SpigotV1m18ChatPluginFactory(plugin)
                    1 -> SpigotV1m18p1ChatPluginFactory(plugin)
                    2 -> SpigotV1m18p2ChatPluginFactory(plugin)
                    else -> SpigotV1m18p2ChatPluginFactory(plugin)
                }

                19 -> when (version.patch) {
                    0 -> SpigotV1m19ChatPluginFactory(plugin)
                    1 -> SpigotV1m19p1ChatPluginFactory(plugin)
                    2 -> SpigotV1m19p2ChatPluginFactory(plugin)
                    else -> SpigotV1m19p3ChatPluginFactory(plugin)
                }

                else -> SpigotLatestChatPluginFactory(plugin)
            }

            // Minecraft 2?
            else -> SpigotLatestChatPluginFactory(plugin)
        }
    }
}
