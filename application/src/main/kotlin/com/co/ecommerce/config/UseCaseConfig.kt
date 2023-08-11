package com.co.ecommerce.config

import com.co.ecommerce.domain.model.price.driven_port.PriceRepositoryPort
import com.co.ecommerce.domain.use_case.price.PriceUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["com.co.ecommerce.domain.use_case"])
class UseCaseConfig {

    @Bean
    fun priceUseCase(priceRepositoryPort: PriceRepositoryPort): PriceUseCase {
        return PriceUseCase(priceRepositoryPort)
    }

}