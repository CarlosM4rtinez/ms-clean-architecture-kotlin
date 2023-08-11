package com.co.ecommerce.api.controller.price.mapper

import com.co.ecommerce.api.controller.price.dto.PriceProductResponseDTO
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
interface PriceDTOMapper {
    fun toDTO(price: Price): PriceProductResponseDTO
    fun toListDTO(customers: List<Price>): List<PriceProductResponseDTO>
}