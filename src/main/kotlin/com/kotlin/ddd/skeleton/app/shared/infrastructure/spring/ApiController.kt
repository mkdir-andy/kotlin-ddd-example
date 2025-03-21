package com.kotlin.ddd.skeleton.app.shared.infrastructure.spring

import com.kotlin.ddd.skeleton.app.shared.domain.bus.command.Command
import com.kotlin.ddd.skeleton.app.shared.domain.bus.command.CommandBus
import com.kotlin.ddd.skeleton.app.shared.domain.bus.query.Query
import com.kotlin.ddd.skeleton.app.shared.domain.bus.query.QueryBus
import com.kotlin.ddd.skeleton.app.shared.domain.bus.query.Response

abstract class ApiController(
    private val commandBus: CommandBus,
    private val queryBus: QueryBus
) {
    protected fun dispatch(command: Command) {
        commandBus.dispatch(command)
    }

    protected fun ask(query: Query): Response? {
        return queryBus.ask(query)
    }
}