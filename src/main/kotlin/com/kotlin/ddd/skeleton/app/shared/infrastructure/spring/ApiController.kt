package com.kotlin.ddd.skeleton.app.shared.infrastructure.spring

import com.kotlin.ddd.skeleton.app.shared.domain.bus.command.BaseCommand
import com.kotlin.ddd.skeleton.app.shared.domain.bus.command.BaseCommandBus

abstract class ApiController(private val commandBus: BaseCommandBus) {
    protected fun dispatch(baseCommand: BaseCommand) {
        commandBus.dispatch(baseCommand)
    }
}