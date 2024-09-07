package com.kotlin.ddd.skeleton.app.shared.infrastructure.bus.command

import com.kotlin.ddd.skeleton.app.shared.domain.bus.command.BaseCommand
import com.kotlin.ddd.skeleton.app.shared.domain.bus.command.BaseCommandBus
import com.kotlin.ddd.skeleton.app.shared.domain.bus.command.BaseCommandHandler
import org.axonframework.commandhandling.SimpleCommandBus
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.commandhandling.gateway.DefaultCommandGateway
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service

@Service
class AxonBaseCommandBus(
    private val commandHandlersInformation: CommandHandlersInformation,
    private val context: ApplicationContext
): BaseCommandBus {
    private lateinit var dispatcher: CommandGateway

    init {
        val commandBus = SimpleCommandBus.builder().build()
        subscribeCommandHandlers(commandBus)
        dispatcher = DefaultCommandGateway.builder().commandBus(commandBus).build()
    }

    override fun dispatch(baseCommand: BaseCommand) {
        try {
            dispatcher.send<BaseCommand>(baseCommand)
        } catch (_: Exception) {
        }
    }

    private fun subscribeCommandHandlers(commandBus: SimpleCommandBus) {
        commandHandlersInformation.handlers().forEach { (commandClass, commandHandlerClass) ->
            run {
                @Suppress("UNCHECKED_CAST")
                val commandHandler = context.getBean(commandHandlerClass) as? BaseCommandHandler<BaseCommand>
                    ?: throw IllegalStateException("Handler bean is not of the expected type.")

                commandBus.subscribe(commandClass.name) { message ->
                    commandHandler.handle(message.payload as BaseCommand)
                }
            }
        }
    }
}