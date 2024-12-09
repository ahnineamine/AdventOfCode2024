plugins {
    kotlin("jvm") version "2.0.21"
}
kotlin {
    jvmToolchain {
        version = 22
    }
}

group = "amine.adventOfCode"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}