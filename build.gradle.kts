buildscript {
  repositories {
    mavenCentral()
  }
  dependencies {
    classpath(Libs.snakeYaml)
  }
}

plugins {
  val kotlinVer = "1.4.21"
  val ktlintVer = "10.0.0"

  kotlin("jvm") version kotlinVer
  kotlin("kapt") version kotlinVer
  kotlin("plugin.spring") version kotlinVer
  kotlin("plugin.jpa") version kotlinVer

  id("org.jlleitschuh.gradle.ktlint") version ktlintVer
  id("org.jlleitschuh.gradle.ktlint-idea") version ktlintVer
  id("org.springframework.boot") version "2.4.5"
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
      jvmTarget = "1.8"
      freeCompilerArgs = listOf("-Xjsr305=strict")
    }
  }
}
