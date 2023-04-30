import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = true

plugins {
    id("com.google.cloud.tools.jib")
}

dependencies {
    api(project(":application"))
    implementation(project(":domain"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
}

tasks {
    springBoot {
        mainClass.set("com.haril.WebApplicationKt")
    }
}

jib {
    from {
        platforms {
            platform {
                architecture = "amd64"
                os = "linux"
            }
        }
    }
    to {
        image = "songkg7/web"
        tags = setOf("latest")
    }
    container {
        jvmFlags = listOf("-Xms512m", "-Xmx512m", "-Dspring.profiles.active=dev")
        ports = listOf("8080")
    }
}
