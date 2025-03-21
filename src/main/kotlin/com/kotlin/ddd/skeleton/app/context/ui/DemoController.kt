package com.kotlin.ddd.skeleton.app.context.ui

import com.kotlin.ddd.skeleton.app.context.application.command.demo.DemoCommand
import com.kotlin.ddd.skeleton.app.context.application.query.demo.DemoQuery
import com.kotlin.ddd.skeleton.app.shared.domain.bus.command.CommandBus
import com.kotlin.ddd.skeleton.app.shared.domain.bus.query.QueryBus
import com.kotlin.ddd.skeleton.app.shared.infrastructure.spring.ApiController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController(
    commandBus: CommandBus,
    queryBus: QueryBus
) : ApiController(commandBus, queryBus) {
    @PostMapping("/context/{id}/demo")
    fun index(@PathVariable id: String) {
        dispatch(DemoCommand(id))
        ask(DemoQuery(id))
    }
}