package com.kotlin.ddd.skeleton.app.shared.infrastructure.bus.command

import com.kotlin.ddd.skeleton.app.shared.domain.bus.command.BaseCommand
import com.kotlin.ddd.skeleton.app.shared.domain.bus.command.BaseCommandBus
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.stereotype.Service

@Service
class AxonBaseCommandBus(private val commandGateway: CommandGateway): BaseCommandBus {
    override fun dispatch(baseCommand: BaseCommand) {
        try {
            commandGateway.send<Any>(baseCommand)
        } catch (_: Exception) {
        }
    }
}