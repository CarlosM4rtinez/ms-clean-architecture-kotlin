package com.co.ecommerce.api.controller.price

import com.co.ecommerce.api.controller.price.dto.PriceProductResponseDTO
import com.co.ecommerce.api.controller.price.mapper.PriceDTOMapper
import com.co.ecommerce.domain.model.price.Price
import com.co.ecommerce.domain.model.price.value_object.PriceProductVO
import com.co.ecommerce.domain.use_case.price.PriceUseCase
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime


@RestController
@RequestMapping(value = ["/api/v1/prices"], produces = [MediaType.APPLICATION_JSON_VALUE])
class PriceController(
        private val priceUseCase: PriceUseCase,
        private val priceDTOMapper: PriceDTOMapper
) {

    @GetMapping
    fun getProductPrice(@RequestParam productId: Int,
                        @RequestParam brandId: Int,
                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  date: LocalDateTime)
            : PriceProductResponseDTO {
        val priceProductVO = PriceProductVO(date, productId, brandId)
        return priceDTOMapper.toDTO(priceUseCase.findByProductAndBrand(priceProductVO))
    }

    @GetMapping("/conditions")
    fun findByConditions(@RequestParam search: String): List<PriceProductResponseDTO> {
        return priceDTOMapper.toListDTO(priceUseCase.findByConditions(search))
    }

    @GetMapping("/all")
    fun findAll(): List<Price> {
        return priceUseCase.findAll()
    }

    @GetMapping("/product/date-now")
    fun getProductPriceWithDateNow(@RequestParam productId: Int,
                                   @RequestParam brandId: Int)
            : PriceProductResponseDTO {
        val priceProductVO = PriceProductVO(LocalDateTime.now(), productId, brandId)
        return priceDTOMapper.toDTO(priceUseCase.findByProductAndBrand(priceProductVO))
    }

}
