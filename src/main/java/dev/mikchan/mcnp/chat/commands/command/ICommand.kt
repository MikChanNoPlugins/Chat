package dev.mikchan.mcnp.chat.commands.command

import org.bukkit.command.CommandExecutor
import org.bukkit.command.TabCompleter

internal interface ICommand : CommandExecutor, TabCompleter
