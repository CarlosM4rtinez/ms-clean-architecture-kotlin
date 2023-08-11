package com.co.ecommerce.domain.use_case.price

import com.co.ecommerce.domain.model.price.Price
import com.co.ecommerce.domain.model.price.driven_port.PriceRepositoryPort
import com.co.ecommerce.domain.model.price.value_object.PriceProductVO

class PriceUseCase(
    private val priceRepositoryPort: PriceRepositoryPort
) {

    fun findByConditions(conditions: String): List<Price> {
        return priceRepositoryPort.findByConditions(conditions)
    }

    fun findByProductAndBrand(priceProductVO: PriceProductVO): Price {
        return priceRepositoryPort.findByProductAndBrand(priceProductVO).first()
    }

    fun findAll() : List<Price> {
        return priceRepositoryPort.findAll()
    }

}