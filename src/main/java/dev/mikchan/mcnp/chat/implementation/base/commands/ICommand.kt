package dev.mikchan.mcnp.chat.implementation.base.commands

import org.bukkit.command.CommandExecutor
import org.bukkit.command.TabCompleter

internal interface ICommand : CommandExecutor, TabCompleter
