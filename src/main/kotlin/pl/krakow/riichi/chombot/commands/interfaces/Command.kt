package pl.krakow.riichi.chombot.commands.interfaces

import discord4j.core.event.domain.message.MessageEvent
import reactor.core.publisher.Mono

interface Command {
    fun execute(event: MessageEvent): Mono<Void>

    fun isApplicable(event: MessageEvent, commandName: String): Boolean
}
