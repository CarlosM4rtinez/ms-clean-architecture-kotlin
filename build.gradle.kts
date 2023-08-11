plugins {
    kotlin("jvm")
    kotlin("plugin.spring") apply false
    kotlin("kapt")
    id("io.spring.dependency-management")
    id("org.springframework.boot") apply false
    id("org.jlleitschuh.gradle.ktlint") apply true
    id("org.sonarqube") apply true
    id("jacoco") apply true
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

allprojects {
    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
    }
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "org.sonarqube")
    apply(plugin = "jacoco")
    apply(plugin = "io.spring.dependency-management")

    java.sourceCompatibility = JavaVersion.VERSION_17
    java.targetCompatibility = JavaVersion.VERSION_17

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        testImplementation(kotlin("test"))
        testImplementation("org.mockito:mockito-junit-jupiter")
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:${property("springBootVersion")}")
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
            allWarningsAsErrors = true
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
    tasks.test {
        finalizedBy(tasks.jacocoTestReport)
    }

    tasks.jacocoTestReport {
        dependsOn(tasks.test)
        reports {
            xml.required.set(true)
            xml.outputLocation.set(file("$buildDir/reports/jacoco.xml"))
            csv.required.set(false)
            html.outputLocation.set(file("$buildDir/reports/jacocoHtml"))
        }
    }
    configurations {
        configurations {
            all {
                exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
            }
        }
    }
}

configure(project.subprojects.filter { it != project(":domain") }) {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "kotlin-spring")

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        api("org.mapstruct:mapstruct:1.5.2.Final")
        kapt("org.mapstruct:mapstruct-processor:1.5.2.Final")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

jacoco {
    toolVersion = "0.8.8"
    reportsDirectory.set(file("$buildDir/reports"))
}

tasks.register<JacocoReport>("jacocoMergedReport") {
    dependsOn(subprojects.map { project -> project.tasks.jacocoTestReport })
    additionalSourceDirs.setFrom(files(subprojects.map { project -> project.sourceSets.main.get().allSource.srcDirs }))
    sourceDirectories.setFrom(files(subprojects.map { project -> project.sourceSets.main.get().allSource.srcDirs }))
    classDirectories.setFrom(files(subprojects.map { project -> project.sourceSets.main.get().output }))
    executionData.setFrom(
        project.fileTree(project.buildDir) {
            include("**/build/jacoco/test.exec")
        }
    )
    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.required.set(true)
    }
}

sonarqube {
    val modules = subprojects.map { subproject ->
        subproject.projectDir.toString().replace(project.projectDir.toString() + "/", "")
    }
    properties {
        property("sonar.projectKey", "ms-ecommerce")
        property("sonar.organization", "Ing_CarlosM4rtinez")
        property("sonar.host.url", "https://sonarcloud.io/")
        property("sonar.login", System.getenv("SONAR_TOKEN"))
        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.modules", modules.joinToString(","))
        property("sonar.sources", "src,settings.gradle.kts,build.gradle.kts,${modules.joinToString(",") { module -> "$module/build.gradle.kts" }}")
        property("sonar.test", "src/test")
        property("sonar.qualitygate.wait", "true")
        property("sonar.java.coveragePlugin", "jacoco")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
        property("sonar.coverage.exclusions", "infrastructure/**,application/**")
    }
}

tasks.wrapper {
    gradleVersion = "7.5.1"
}
