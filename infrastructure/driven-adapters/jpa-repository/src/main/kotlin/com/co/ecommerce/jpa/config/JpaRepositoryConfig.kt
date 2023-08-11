package com.co.ecommerce.jpa.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["com.co.ecommerce.jpa"])
class JpaRepositoryConfig
