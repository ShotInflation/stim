import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.21"
    application
}

group = "org.shotinflation"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val jacksonVersion = "2.10.0"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.fasterxml.jackson.core", "jackson-databind", jacksonVersion)
    implementation("com.fasterxml.jackson.module", "jackson-module-kotlin", jacksonVersion)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}