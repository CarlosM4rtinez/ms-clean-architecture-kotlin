package com.co.ecommerce.jpa.adapter.customer

import com.co.ecommerce.domain.model.price.Price
import com.co.ecommerce.domain.model.price.driven_port.PriceRepositoryPort
import com.co.ecommerce.domain.model.price.value_object.PriceProductVO
import com.co.ecommerce.jpa.adapter.customer.data.PriceMapper
import io.github.perplexhub.rsql.RSQLJPASupport
import org.springframework.stereotype.Component

@Component
class PriceRepositoryAdapter (
    private val priceJpaRepository: PriceJpaRepository,
    private val priceMapper: PriceMapper
) : PriceRepositoryPort {

    override fun findByConditions(conditions: String): List<Price> {
        val prices = priceJpaRepository.findAll(RSQLJPASupport.toSpecification(conditions))
        return priceMapper.toListModel(prices)
    }

    override fun findByProductAndBrand(priceProductVO: PriceProductVO): List<Price> {
        val prices = priceJpaRepository.findByProductAndBrandAndDate(priceProductVO.productId, priceProductVO.brandId, priceProductVO.date)
        return priceMapper.toListModel(prices)
    }

    override fun findAll(): List<Price> {
        priceJpaRepository.findAll().forEach {
            println(it.price)
            println(it.product?.id)
        }
        return priceMapper.toListModel(priceJpaRepository.findAll())
    }

}