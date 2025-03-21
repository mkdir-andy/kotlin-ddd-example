package com.kotlin.ddd.skeleton.app.shared.domain.bus.query

class QueryHandlerExecutionError(error: Throwable): RuntimeException(error)