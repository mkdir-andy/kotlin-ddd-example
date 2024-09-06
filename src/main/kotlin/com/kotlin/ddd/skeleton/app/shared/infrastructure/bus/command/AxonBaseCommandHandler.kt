package com.kotlin.ddd.skeleton.app.shared.infrastructure.bus.command

import com.kotlin.ddd.skeleton.app.shared.domain.bus.command.BaseCommand
import com.kotlin.ddd.skeleton.app.shared.domain.bus.command.BaseCommandHandler
import com.kotlin.ddd.skeleton.app.shared.domain.bus.command.CommandHandlerExecutionError
import org.axonframework.commandhandling.CommandHandler
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service

@Service
class AxonBaseCommandHandler(private val commandHandlersInformation: CommandHandlersInformation, private val context: ApplicationContext) {
    @CommandHandler
    fun handle(command: BaseCommand) {
        try {
            val commandHandlerClass = commandHandlersInformation.search(command::class.java)
            @Suppress("UNCHECKED_CAST")
            val baseCommandHandler = context.getBean(commandHandlerClass) as? BaseCommandHandler<BaseCommand>
                ?: throw IllegalStateException("Handler bean is not of the expected type.")

            baseCommandHandler.handle(command)
        } catch (error: Throwable) {
            throw CommandHandlerExecutionError(error)
        }
    }
}