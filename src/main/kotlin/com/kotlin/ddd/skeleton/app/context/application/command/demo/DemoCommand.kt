package com.kotlin.ddd.skeleton.app.context.application.command.demo

import com.kotlin.ddd.skeleton.app.shared.domain.bus.command.Command

data class DemoCommand(val id: String): Command