dependencies {
    /* Domain */
    implementation(project(":domain"))

    /* Driven Adapters */
    implementation(project(":jpa-repository"))

    /* Entry Points */
    implementation(project(":rest-api"))

    /* External Dependencies */
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
    implementation("org.springframework.cloud:spring-cloud-starter-config")

}

tasks.getByName<Jar>("jar") {
    enabled = false
}

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    this.archiveFileName.set("${project.parent?.name}.${archiveExtension.get()}")
}
