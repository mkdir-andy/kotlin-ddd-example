package com.kotlin.ddd.skeleton.app.shared.domain.bus.command

interface BaseCommandBus {
    fun dispatch(baseCommand: BaseCommand)
}