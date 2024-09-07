package com.kotlin.ddd.skeleton.app.context.ui

import com.kotlin.ddd.skeleton.app.context.application.command.demo.DemoCommand
import com.kotlin.ddd.skeleton.app.shared.domain.bus.command.BaseCommandBus
import com.kotlin.ddd.skeleton.app.shared.infrastructure.spring.ApiController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController(commandBus: BaseCommandBus) : ApiController(commandBus) {
    @PostMapping("/context/{id}/demo")
    fun index(@PathVariable id: String) {
        dispatch(DemoCommand(id))
    }
}