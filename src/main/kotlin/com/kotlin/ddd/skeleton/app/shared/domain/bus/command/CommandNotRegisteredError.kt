package com.kotlin.ddd.skeleton.app.shared.domain.bus.command

class CommandNotRegisteredError(command: Class<out Command>): Exception("The command <${command}> hasn't a command handler associated")