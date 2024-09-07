package com.kotlin.ddd.skeleton.app.shared.infrastructure.bus.command

import com.kotlin.ddd.skeleton.app.shared.domain.bus.command.BaseCommand
import com.kotlin.ddd.skeleton.app.shared.domain.bus.command.BaseCommandHandler
import org.reflections.Reflections
import org.springframework.stereotype.Service
import java.lang.reflect.ParameterizedType

@Service
class CommandHandlersInformation() {
    private lateinit var indexedBaseCommandHandlers: HashMap<Class<out BaseCommand>, Class<out BaseCommandHandler<*>>>

    init {
        val reflections = Reflections("com.kotlin.ddd.skeleton")
        val classes = reflections.getSubTypesOf(BaseCommandHandler::class.java)
        indexedBaseCommandHandlers = formatHandlers(classes)
    }

    fun handlers(): HashMap<Class<out BaseCommand>, Class<out BaseCommandHandler<*>>> = indexedBaseCommandHandlers

    private fun formatHandlers(commandHandlers: Set<Class<out BaseCommandHandler<*>>>): HashMap<Class<out BaseCommand>, Class<out BaseCommandHandler<*>>> {
        val handlers = HashMap<Class<out BaseCommand>, Class<out BaseCommandHandler<*>>>()

        for (handler in commandHandlers) {
            val paramType = handler.genericInterfaces[0] as ParameterizedType
            @Suppress("UNCHECKED_CAST")
            val commandClass = paramType.actualTypeArguments[0] as Class<out BaseCommand>

            handlers[commandClass] = handler
        }

        return handlers
    }
}