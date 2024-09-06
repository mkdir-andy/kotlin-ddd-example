package com.kotlin.ddd.skeleton.app.shared.domain.bus.command

interface BaseCommandHandler<T: BaseCommand> {
    fun handle(command: T)
}