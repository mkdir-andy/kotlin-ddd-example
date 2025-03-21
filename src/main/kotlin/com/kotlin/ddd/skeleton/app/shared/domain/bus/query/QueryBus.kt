package com.kotlin.ddd.skeleton.app.shared.domain.bus.query

interface QueryBus {
    fun ask(query: Query): Response?
}