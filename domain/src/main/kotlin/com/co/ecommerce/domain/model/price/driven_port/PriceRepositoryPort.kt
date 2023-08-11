package com.co.ecommerce.domain.model.price.driven_port

import com.co.ecommerce.domain.model.price.Price
import com.co.ecommerce.domain.model.price.value_object.PriceProductVO

interface PriceRepositoryPort {

    fun findByProductAndBrand(priceProductVO: PriceProductVO): List<Price>
    fun findByConditions(conditions: String): List<Price>
    fun findAll(): List<Price>

}