dependencies {
    implementation("ch.qos.logback:logback-classic")
    implementation("org.ehcache:ehcache")
    implementation(project(mapOf("path" to ":L18-JDBC")))

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation("org.assertj:assertj-core")
    testImplementation("org.mockito:mockito-junit-jupiter")
    implementation("com.google.code.gson:gson:2.10.1")

    implementation("org.flywaydb:flyway-core")
    implementation("com.zaxxer:HikariCP")
    implementation("org.postgresql:postgresql")

    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")

    implementation("ch.qos.logback:logback-classic")

    implementation("com.google.guava:guava")
}