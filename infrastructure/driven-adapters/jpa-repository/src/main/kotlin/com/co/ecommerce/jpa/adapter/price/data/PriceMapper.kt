package com.co.ecommerce.jpa.adapter.customer.data

import com.co.ecommerce.domain.model.price.Price
import org.mapstruct.Mapper
import org.mapstruct.NullValueCheckStrategy
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.ReportingPolicy

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
)
interface PriceMapper {
    fun toModel(priceEntity: PriceEntity): Price
    fun toData(customer: Price): PriceEntity
    fun toListModel(customersData: List<PriceEntity>): List<Price>
    fun toListData(customers: List<Price>): List<PriceEntity>
}