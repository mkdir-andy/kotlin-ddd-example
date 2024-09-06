package com.kotlin.ddd.skeleton.app.shared.domain.bus.command

class CommandHandlerExecutionError(error: Throwable): RuntimeException(error)