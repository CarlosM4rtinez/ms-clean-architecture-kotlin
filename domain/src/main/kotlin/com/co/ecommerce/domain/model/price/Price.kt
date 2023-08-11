package com.co.ecommerce.domain.model.price

import com.co.ecommerce.domain.model.brand.Brand
import com.co.ecommerce.domain.model.price.constant.CurrencyType
import com.co.ecommerce.domain.model.product.Product
import java.time.LocalDateTime

data class Price (
    val id: Int,
    val product: Product,
    val brand: Brand,
    val currency: CurrencyType,
    val price: Double,
    val priority: Int,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime
) {

    override fun toString(): String {
        return "Price(id=$id, product=$product, brand=$brand, currency=$currency, price=$price, priority=$priority, startDate=$startDate, endDate=$endDate)"
    }
}