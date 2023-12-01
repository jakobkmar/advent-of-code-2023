plugins {
    kotlin("jvm") version "1.9.21"
}

group = "net.jakobk"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.ajalt.mordant:mordant:2.2.0")
}

kotlin {
    jvmToolchain(17)
}

sourceSets {
    main {
        resources.srcDirs("input")
        kotlin.srcDirs("src/kotlin")
    }
}
