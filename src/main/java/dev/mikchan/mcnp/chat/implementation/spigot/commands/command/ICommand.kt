package dev.mikchan.mcnp.chat.implementation.spigot.commands.command

import org.bukkit.command.CommandExecutor
import org.bukkit.command.TabCompleter

internal interface ICommand : CommandExecutor, TabCompleter
