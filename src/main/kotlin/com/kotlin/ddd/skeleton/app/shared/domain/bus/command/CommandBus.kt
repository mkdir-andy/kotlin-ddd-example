package com.kotlin.ddd.skeleton.app.shared.domain.bus.command

interface CommandBus {
    fun dispatch(command: Command)
}