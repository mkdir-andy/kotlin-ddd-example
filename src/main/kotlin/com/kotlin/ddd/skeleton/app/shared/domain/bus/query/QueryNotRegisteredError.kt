package com.kotlin.ddd.skeleton.app.shared.domain.bus.query

class QueryNotRegisteredError(query: Class<out Query>): Exception("The query <${query}> hasn't a query handler associated")