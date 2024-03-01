rootProject.name = "otusJava"
include("L01-gradle")
include("L04")
include("L06-annotations")
include("L08-GC")
include("L12-SOLID")
include("L15-Patterns")
include("L16-Serialization")
include("L10-ByteCode")
include("L18-JDBC")
include("L21-Cache")
include("L22-Hibernate")
include("L24-Web")
include("L25-IOC")

pluginManagement {
    val jgitver: String by settings
    val dependencyManagement: String by settings
    val springframeworkBoot: String by settings
    val johnrengelmanShadow: String by settings
    val jib: String by settings
    val protobufVer: String by settings
    val sonarlint: String by settings
    val spotless: String by settings

    plugins {
        id("fr.brouillard.oss.gradle.jgitver") version jgitver
        id("io.spring.dependency-management") version dependencyManagement
        id("org.springframework.boot") version springframeworkBoot
        id("com.github.johnrengelman.shadow") version johnrengelmanShadow
        id("com.google.cloud.tools.jib") version jib
        id("com.google.protobuf") version protobufVer
        id("name.remal.sonarlint") version sonarlint
        id("com.diffplug.spotless") version spotless
    }
}
