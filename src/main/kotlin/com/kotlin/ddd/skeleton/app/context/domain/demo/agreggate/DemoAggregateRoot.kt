package com.kotlin.ddd.skeleton.app.context.domain.demo.agreggate

import com.kotlin.ddd.skeleton.app.shared.domain.value_objects.AggregateRoot

class DemoAggregateRoot private constructor(private val id: String): AggregateRoot(id) {
    companion object Creator {
        fun create(id: String): DemoAggregateRoot {
            val demoAggregateRoot = DemoAggregateRoot(id)
            return demoAggregateRoot
        }
    }
}