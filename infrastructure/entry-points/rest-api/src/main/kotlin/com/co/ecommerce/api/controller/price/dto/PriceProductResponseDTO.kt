package com.co.ecommerce.api.controller.price.dto

import com.co.ecommerce.domain.model.brand.Brand
import com.co.ecommerce.domain.model.price.constant.CurrencyType
import com.co.ecommerce.domain.model.product.Product
import java.time.LocalDateTime

data class PriceProductResponseDTO (

    val product: Product,
    val brand: Brand,
    val currency: CurrencyType,
    val price: Double,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime

) {
}


