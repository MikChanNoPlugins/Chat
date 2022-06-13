package dev.mikchan.chat.config

import dev.mikchan.chat.utility.IReloadable
import java.util.*

internal abstract class ReloadableConfig : IConfig {
    private var subscribers: List<IReloadable> = Collections.synchronizedList(listOf())

    override fun reload(): Boolean {
        val tmp = mutableListOf<IReloadable>()

        synchronized(subscribers) {
            tmp.addAll(subscribers)
        }

        for (entity in tmp) {
            entity.reload()
        }

        return true
    }

    override fun subscribe(entity: IReloadable) {
        val tmp = mutableListOf<IReloadable>()

        synchronized(subscribers) {
            tmp.addAll(subscribers)
        }

        tmp.add(entity)

        subscribers = Collections.synchronizedList(tmp)
    }

    override fun unsubscribe(entity: IReloadable) {
        val tmp = mutableListOf<IReloadable>()

        synchronized(subscribers) {
            tmp.addAll(subscribers)
        }

        tmp.remove(entity)

        subscribers = Collections.synchronizedList(tmp)
    }
}