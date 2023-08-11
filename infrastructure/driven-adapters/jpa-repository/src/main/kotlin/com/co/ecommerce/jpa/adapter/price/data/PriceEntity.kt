package com.co.ecommerce.jpa.adapter.customer.data

import com.co.ecommerce.domain.model.price.constant.CurrencyType
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "PRICES")
class PriceEntity {

    @Id
    var id: Int? = null

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "product_id")
    var product: ProductEntity? = null

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "brand_id")
    var brand: BrandEntity? = null

    @Enumerated(EnumType.STRING)
    var currency: CurrencyType? = null

    var price: Double? = null
    var priority: Int? = null

    @Column(name = "start_date", nullable = false)
    var startDate: LocalDateTime? = null

    @Column(name = "end_date", nullable = false)
    var endDate: LocalDateTime? = null

    @Entity
    @Table(name = "BRANDS")
    class BrandEntity {
        @Id
        var id: Int? = null
        var name: String? = null
    }

    @Entity
    @Table(name = "PRODUCTS")
    class ProductEntity {
        @Id
        var id: Int? = null
    }

}