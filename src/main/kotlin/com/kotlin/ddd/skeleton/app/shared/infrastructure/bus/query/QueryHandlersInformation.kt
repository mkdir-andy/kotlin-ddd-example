package com.kotlin.ddd.skeleton.app.shared.infrastructure.bus.query

import com.kotlin.ddd.skeleton.app.shared.domain.bus.query.Query
import com.kotlin.ddd.skeleton.app.shared.domain.bus.query.QueryHandler
import org.reflections.Reflections
import org.springframework.stereotype.Service
import java.lang.reflect.ParameterizedType

@Service
class QueryHandlersInformation() {
    private lateinit var indexedQueryHandlers: HashMap<Class<out Query>, Class<out QueryHandler<*,*>>>

    init {
        val reflections = Reflections("com.kotlin.ddd.skeleton")
        val classes = reflections.getSubTypesOf(QueryHandler::class.java)
        indexedQueryHandlers = formatHandlers(classes)
    }

    fun handlers(): HashMap<Class<out Query>, Class<out QueryHandler<*,*>>> = indexedQueryHandlers

    private fun formatHandlers(queryHandlers: Set<Class<out QueryHandler<*,*>>>): HashMap<Class<out Query>, Class<out QueryHandler<*,*>>> {
        val handlers = HashMap<Class<out Query>, Class<out QueryHandler<*,*>>>()

        for (handler in queryHandlers) {
            val paramType = handler.genericInterfaces[0] as ParameterizedType
            @Suppress("UNCHECKED_CAST")
            val queryClass = paramType.actualTypeArguments[0] as Class<out Query>

            handlers[queryClass] = handler
        }

        return handlers
    }
}