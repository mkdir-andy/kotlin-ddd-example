package com.kotlin.ddd.skeleton.app.context.application.query.demo

import com.kotlin.ddd.skeleton.app.shared.domain.bus.query.QueryHandler
import org.springframework.stereotype.Service

@Service
class DemoQueryHandler: QueryHandler<DemoQuery, DemoResponse> {

    override fun handle(query: DemoQuery): DemoResponse? {
        println("Aggregate Root <${query.id}> Returned!!! ")
        return null
    }
}