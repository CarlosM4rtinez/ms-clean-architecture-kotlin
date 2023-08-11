package com.co.ecommerce.jpa.adapter.customer

import com.co.ecommerce.jpa.adapter.customer.data.PriceEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Component
@Repository
interface PriceJpaRepository : JpaRepository<PriceEntity, Int>,
                               JpaSpecificationExecutor<PriceEntity> {

    @Query("SELECT * FROM prices p WHERE p.product_id = ?1 AND p.brand_id = ?2 AND ?3 BETWEEN p.start_date AND p.end_date ORDER BY p.priority", nativeQuery = true)
    fun findByProductAndBrandAndDate(productId: Int, brandId: Int, date: LocalDateTime): List<PriceEntity>

}