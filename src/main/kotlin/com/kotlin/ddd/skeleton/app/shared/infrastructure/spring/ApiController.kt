package com.kotlin.ddd.skeleton.app.shared.infrastructure.spring

import com.kotlin.ddd.skeleton.app.shared.domain.bus.command.Command
import com.kotlin.ddd.skeleton.app.shared.domain.bus.command.CommandBus

abstract class ApiController(private val commandBus: CommandBus) {
    protected fun dispatch(command: Command) {
        commandBus.dispatch(command)
    }
}