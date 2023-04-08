import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

plugins {
    id("org.springframework.boot")
    kotlin("plugin.spring")
}

dependencies {
    api(project(":infrastructure"))
    implementation("org.springframework.boot:spring-boot-starter-web")
}
