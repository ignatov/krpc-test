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

    implementation("org.jetbrains.krpc:krpc-runtime:1.9.10-beta-1")
    implementation("org.jetbrains.krpc:krpc-runtime-client:1.9.10-beta-1")
    implementation(kotlin("test-junit"))
    testImplementation("org.jetbrains.krpc:krpc-runtime-test:1.9.10-beta-1")
}

kotlin {
    jvmToolchain(8)
}
