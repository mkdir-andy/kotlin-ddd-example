package com.kotlin.ddd.skeleton.app.shared.infrastructure.bus.query

import com.kotlin.ddd.skeleton.app.shared.domain.bus.query.Query
import com.kotlin.ddd.skeleton.app.shared.domain.bus.query.QueryBus
import com.kotlin.ddd.skeleton.app.shared.domain.bus.query.QueryHandler
import com.kotlin.ddd.skeleton.app.shared.domain.bus.query.Response
import org.axonframework.queryhandling.DefaultQueryGateway
import org.axonframework.queryhandling.QueryGateway
import org.axonframework.queryhandling.SimpleQueryBus
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class AxonQueryBus(
    private val queryHandlersInformation: QueryHandlersInformation,
    private val context: ApplicationContext
): QueryBus {
    private lateinit var dispatcher: QueryGateway

    init {
        val queryBus = SimpleQueryBus.builder().build()
        subscribeQueryHandlers(queryBus)
        dispatcher = DefaultQueryGateway.builder().queryBus(queryBus).build()
    }

    override fun ask(query: Query): Response? {
        try {
            val response: CompletableFuture<Response> = dispatcher.query<Response, Query>(query, Response::class.java)

            return response.get()
        } catch (_: Exception) {
            return null
        }
    }

    private fun subscribeQueryHandlers(queryBus: SimpleQueryBus) {
        queryHandlersInformation.handlers().forEach { (queryClass, queryHandlerClass) ->
            run {
                @Suppress("UNCHECKED_CAST")
                val queryHandler = context.getBean(queryHandlerClass) as? QueryHandler<Query, Response>
                    ?: throw IllegalStateException("Handler bean is not of the expected type.")

                queryBus.subscribe<Response>(queryClass.name, Response::class.java) { message ->
                    queryHandler.handle(message.payload as Query)
                }
            }
        }
    }
}