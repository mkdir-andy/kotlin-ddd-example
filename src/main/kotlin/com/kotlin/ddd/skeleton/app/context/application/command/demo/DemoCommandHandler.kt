package com.kotlin.ddd.skeleton.app.context.application.command.demo

import com.kotlin.ddd.skeleton.app.context.domain.demo.agreggate.DemoAggregateRoot
import com.kotlin.ddd.skeleton.app.shared.domain.bus.command.CommandHandler
import org.springframework.stereotype.Service

@Service
class DemoCommandHandler: CommandHandler<DemoCommand> {
    override fun handle(command: DemoCommand) {
        val demoAggregateRoot = DemoAggregateRoot.create(command.id)
        println("Aggregate Root <${demoAggregateRoot.id()}> Created!!! ")
    }
}