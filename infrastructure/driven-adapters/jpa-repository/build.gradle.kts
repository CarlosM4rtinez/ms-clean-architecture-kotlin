dependencies {
    implementation(project(":domain"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.h2database:h2:2.1.214")
    implementation("io.github.perplexhub:rsql-jpa-spring-boot-starter:6.0.4")
    implementation("io.github.perplexhub:rsql-querydsl-spring-boot-starter:6.0.4")

}
