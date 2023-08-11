package com.co.ecommerce.api.controller.price.dto

import java.time.LocalDateTime

data class PriceProductRequestDTO (
    val date: LocalDateTime,
    val productId: Int,
    val brandId: Int
) {
}