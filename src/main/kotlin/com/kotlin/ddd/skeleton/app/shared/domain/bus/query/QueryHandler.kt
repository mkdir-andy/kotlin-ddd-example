package com.kotlin.ddd.skeleton.app.shared.domain.bus.query

interface QueryHandler<Q: Query, R: Response?> {
    fun handle(query: Q): R?
}