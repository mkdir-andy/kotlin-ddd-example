package com.kotlin.ddd.skeleton.app.shared.domain.value_objects

open class AggregateRoot(private val id: String) {
    fun id() = id
}