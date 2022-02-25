buildscript {
  repositories {
    mavenCentral()
  }
}

plugins {
  val kotlinVer = "1.6.10"  // https://kotlinlang.org/docs/releases.html
  val ktlintVer = "10.2.1"  // https://github.com/JLLeitschuh/ktlint-gradle/releases
  val springVer = "2.6.3"  // https://github.com/spring-projects/spring-boot/releases

  kotlin("jvm") version kotlinVer
  kotlin("kapt") version kotlinVer
  kotlin("plugin.spring") version kotlinVer
  kotlin("plugin.jpa") version kotlinVer

  id("org.jlleitschuh.gradle.ktlint") version ktlintVer
  id("org.jlleitschuh.gradle.ktlint-idea") version ktlintVer
  id("org.springframework.boot") version springVer
  id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

allprojects {
  repositories {
    mavenCentral()
  }
}

subprojects {
  apply(plugin = "kotlin")
  apply(plugin = "kotlin-kapt")

  tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
      jvmTarget = "17"
      freeCompilerArgs = listOf("-Xjsr305=strict")
    }
  }
}
