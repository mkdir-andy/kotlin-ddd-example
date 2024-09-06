package com.kotlin.ddd.skeleton.app.context.application.command.demo

import com.kotlin.ddd.skeleton.app.shared.domain.bus.command.BaseCommandHandler
import org.springframework.stereotype.Service

@Service
class DemoCommandHandler: BaseCommandHandler<DemoCommand> {
    override fun handle(command: DemoCommand) {
        println("Hello world!")
    }
}