package com.kotlin.ddd.skeleton.app.shared.infrastructure.bus.command

import com.kotlin.ddd.skeleton.app.shared.domain.bus.command.Command
import com.kotlin.ddd.skeleton.app.shared.domain.bus.command.CommandHandler
import org.reflections.Reflections
import org.springframework.stereotype.Service
import java.lang.reflect.ParameterizedType

@Service
class CommandHandlersInformation() {
    private lateinit var indexedCommandHandlers: HashMap<Class<out Command>, Class<out CommandHandler<*>>>

    init {
        val reflections = Reflections("com.kotlin.ddd.skeleton")
        val classes = reflections.getSubTypesOf(CommandHandler::class.java)
        indexedCommandHandlers = formatHandlers(classes)
    }

    fun handlers(): HashMap<Class<out Command>, Class<out CommandHandler<*>>> = indexedCommandHandlers

    private fun formatHandlers(commandHandlers: Set<Class<out CommandHandler<*>>>): HashMap<Class<out Command>, Class<out CommandHandler<*>>> {
        val handlers = HashMap<Class<out Command>, Class<out CommandHandler<*>>>()

        for (handler in commandHandlers) {
            val paramType = handler.genericInterfaces[0] as ParameterizedType
            @Suppress("UNCHECKED_CAST")
            val commandClass = paramType.actualTypeArguments[0] as Class<out Command>

            handlers[commandClass] = handler
        }

        return handlers
    }
}