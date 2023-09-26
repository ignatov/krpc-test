import org.jetbrains.kotlin.gradle.plugin.PLUGIN_CLASSPATH_CONFIGURATION_NAME


plugins {
    kotlin("jvm") version "1.9.10"
    kotlin("plugin.serialization") version "1.9.10"
    id("com.google.devtools.ksp") version "1.9.10-1.0.13"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    maven(url = "https://maven.pkg.jetbrains.space/public/p/krpc/maven")
    mavenCentral()
}


dependencies {
    ksp("org.jetbrains.krpc:krpc-ksp-plugin:1.9.10-beta-1")
    PLUGIN_CLASSPATH_CONFIGURATION_NAME("org.jetbrains.krpc:krpc-compiler-plugin:1.9.10-beta-1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}
