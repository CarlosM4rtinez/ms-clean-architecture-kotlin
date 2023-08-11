package com.co.ecommerce.api.commons

import org.springframework.stereotype.Component

@Component
class SpringSearchUtil {

    fun queryAsRSQL(search: String): String {
        val replacements = mapOf(":" to "==", "AND" to "and", "OR" to "or")
        var query = search
        replacements.forEach { (oldValue, newValue) -> query = query.replace(oldValue, newValue) }
        return query;
    }

}