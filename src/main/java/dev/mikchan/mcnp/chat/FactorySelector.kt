package dev.mikchan.mcnp.chat

import dev.mikchan.mcnp.chat.contract.IChatPluginFactory
import dev.mikchan.mcnp.chat.implementation.pre19spigot.Pre19SpigotChatPluginFactory
import dev.mikchan.mcnp.chat.implementation.spigot.SpigotChatPluginFactory


internal object FactorySelector {
    @Suppress("unused")
    private class Version(val major: Int, val minor: Int, val patch: Int)

    private fun getVersion(plugin: ChatPlugin): Version? {
        val versionRegex = Regex("""(\d+)\.(\d+)\.(\d+)""")
        val match = versionRegex.find(plugin.server.bukkitVersion) ?: return null
        val (sMajor, sMinor, sPatch) = match.destructured

        val major = sMajor.toIntOrNull() ?: return null
        val minor = sMinor.toIntOrNull() ?: return null
        val patch = sPatch.toIntOrNull() ?: return null

        return Version(major, minor, patch)
    }

    fun selectFactory(plugin: ChatPlugin): IChatPluginFactory {
        val version = getVersion(plugin) ?: return SpigotChatPluginFactory(plugin)

        if (version.major == 1 && version.minor < 19) {
            return Pre19SpigotChatPluginFactory(plugin)
        }

        return SpigotChatPluginFactory(plugin)
    }
}
