package com.kotlin.ddd.skeleton.app.shared.domain.bus.command

class CommandNotRegisteredError(baseCommand: Class<out BaseCommand>): Exception("The command <${baseCommand}> hasn't a command handler associated")