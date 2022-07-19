[![Downloads](https://pluginbadges.glitch.me/api/v1/dl/Downloads-limegreen.svg?spigot=mikchan%25E3%2581%25AEchat.103501&github=MikChanNoPlugins%2FChat&style=flat)](https://www.spigotmc.org/resources/mikchan%E3%81%AEchat.103501/)
[![Java CI](https://github.com/MikChanNoPlugins/Chat/actions/workflows/build.yaml/badge.svg)](https://github.com/MikChanNoPlugins/Chat/actions/workflows/build.yaml)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/f7bb532885f14bc28dd589b9ebec7427)](https://www.codacy.com/gh/MikChanNoPlugins/Chat/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=MikChanNoPlugins/Chat&amp;utm_campaign=Badge_Grade)

# MikChanの Chat
A minimalistic chat plugin.

## Supported Versions

Spigot/Paper/Bukkit 1.16.5+

## Features

### Global/Local/Private Messages
The plugin adds support of 3 types of messages: Global, Local and Private. 
-   Global messages are visible to all players on the server.
-   Local messages are only visible to players within the certain radius from the sender.
-   Private messages are only visible to the specific player the sender specified.

If needed, local messages can be disabled by setting
```yaml
enableLocal: false
```

If local messages are enabled, all messages by default become local messages. To send a global message, the text of the message has to start with specific prefix. By default it's `!`.
E.g.
```log
> Hello World!
[Local] Hello World!

> !Hello World!
[Global] Hello World!
```

To change the prefix for global messages, you need to change this setting
```yaml
globalPrefix: "!"
```

To send a private message, you should use the command
```log
/msg MikChan Hello, MikChan!
```

Also you can use this command to reply back to the last person who sent a message to you
```log
/r Hello Back!
```

### Formatting

The plugin also lets you change how messages look like by modifying these settings.
```yaml
globalTemplate: '<:player:> :message:'
localTemplate: '<:player:> :message:'
privateTemplate: '<:player_from: to :player_to:> :message:'
consoleTemplate: '<CONSOLE to :player_to:> :message:'
```

As you can notice, there are some unusual looking placeholders.
-   `:player:` is replaced with the senders name.
-   `:player_from:` is replaced with the senders name. Works only in `privateTemplate`.
-   `:player_to:` is replaced with the recipients name. Works only in `privateTemplate` and `consoleTemplate`.
-   `:message:` is replaced with the text of the message.

If you wonder why `consoleTemplate` is needed: the private messages can be sent from the console, but since console has no username, it has to be formatted separately.

### Placeholder API (PAPI)
This plugin supports PAPI placeholders. There are PAPI specific settings in the config.
```yaml
playerTemplate: '%player_name%'
fromTemplate: '%player_name%'
toTemplate: '%player_name%'
```

If PAPI is present
-   `:player:` will be replaced with `playerTemplate`.
-   `:player_from:` will be replaced with `fromTemplate`.
-   `:player_to:` will be replaced with `toTemplate`.

If PAPI is not present in the plugin list, these settings will be simply ignored.

## Support the creator
[!["Buy Me A Coffee"](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://www.buymeacoffee.com/mcnp)

## Stats

[![bStats](https://bstats.org/signatures/bukkit/MikChanNoChat.svg)](https://bstats.org/plugin/bukkit/MikChanNoChat/15823)
