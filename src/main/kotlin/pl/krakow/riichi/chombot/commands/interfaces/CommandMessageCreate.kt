package pl.krakow.riichi.chombot.commands.interfaces

import discord4j.core.event.domain.message.MessageCreateEvent
import discord4j.core.event.domain.message.MessageEvent
import reactor.core.publisher.Mono

abstract class CommandMessageCreate : Command {
    override fun execute(event: MessageEvent): Mono<Void> {
        return when (event) {
            is MessageCreateEvent -> executeMessageCreate(event)
            else -> Mono.empty()
        }
    }

    override fun isApplicable(event: MessageEvent, commandName: String): Boolean {
        return when (event) {
            is MessageCreateEvent -> isApplicableMessageCreate(event, commandName)
            else -> false
        }
    }

    abstract fun executeMessageCreate(event: MessageCreateEvent): Mono<Void>

    open fun isApplicableMessageCreate(event: MessageCreateEvent, commandName: String): Boolean {
        if (!event.message.content.isPresent) {
            return false
        }

        return event.message.content.get().startsWith("!$commandName")
    }
}