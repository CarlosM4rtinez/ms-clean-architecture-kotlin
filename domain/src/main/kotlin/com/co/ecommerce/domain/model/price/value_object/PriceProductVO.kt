package com.co.ecommerce.domain.model.price.value_object

import java.time.LocalDateTime

class PriceProductVO (
    val date: LocalDateTime,
    val productId: Int,
    val brandId: Int
) {

}