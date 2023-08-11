rootProject.name = "ms-ecommerce"

pluginManagement {
    val pluginVersion: String by settings
    val kotlinVersion: String by settings
    val springManagementVersion: String by settings
    val springBootVersion: String by settings
    val ktLintVersion: String by settings
    val sonarqubeVersion: String by settings
    val nexusHost: String by settings

    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        kotlin("plugin.allopen") version kotlinVersion
        kotlin("kapt") version kotlinVersion
        id("io.spring.dependency-management") version springManagementVersion
        id("org.springframework.boot") version springBootVersion
        id("org.jlleitschuh.gradle.ktlint") version ktLintVersion
        id("org.sonarqube") version sonarqubeVersion
    }
}

include(":application")
project(":application").projectDir = file("./application")

include(":domain")
project(":domain").projectDir = file("./domain")

include(":rest-api")
project(":rest-api").projectDir = file("./infrastructure/entry-points/rest-api")

include(":jpa-repository")
project(":jpa-repository").projectDir = file("./infrastructure/driven-adapters/jpa-repository")