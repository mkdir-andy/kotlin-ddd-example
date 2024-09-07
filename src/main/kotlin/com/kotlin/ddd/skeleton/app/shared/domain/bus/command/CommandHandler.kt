package com.kotlin.ddd.skeleton.app.shared.domain.bus.command

interface CommandHandler<T: Command> {
    fun handle(command: T)
}