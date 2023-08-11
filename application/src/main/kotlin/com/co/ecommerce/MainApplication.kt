package com.co.ecommerce

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.co.*"])
@ConfigurationPropertiesScan
class MainApplication

fun main(args: Array<String>) {
    runApplication<com.co.ecommerce.MainApplication>(*args)
}