plugins {
  id("org.flywaydb.flyway") version "8.5.1"
}

apply(plugin = "kotlin-spring")
apply(plugin = "kotlin-jpa")
apply(plugin = "io.spring.dependency-management")
apply(plugin = "org.springframework.boot")

dependencies {
  implementation(platform(Libs.Boms.kotlinBom))

  implementation(Libs.jacksonModuleKotlin)
  implementation(Libs.Swagger.starter)
  implementation(Libs.Swagger.swagger2)
  implementation(Libs.Swagger.swaggerUi)
  implementation(Libs.MapStruct.mapstruct)
  implementation(Libs.SpringBoot.starterActuator)
  implementation(Libs.SpringBoot.starterWeb)
  implementation(Libs.SpringBoot.starterDataJpa)
  implementation(Libs.flywayCore)

  kapt(Libs.MapStruct.mapstructProcessor)

  runtimeOnly(Libs.mysqlConnectorJava)

  testImplementation(Libs.Kotest.runnerJunit5)
  testImplementation(Libs.Kotest.assertionsCore)
  testImplementation(Libs.fixture)
  testImplementation(Libs.mockk)
  testImplementation(Libs.springMockk)
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks.named("check") {
  dependsOn(":ktlintCheck")
}

sourceSets {
  create("intTest") {
    compileClasspath += sourceSets.main.get().output
    runtimeClasspath += sourceSets.main.get().output
  }
}

val intTestImplementation: Configuration by configurations.getting {
  extendsFrom(configurations.implementation.get())
}

configurations["intTestImplementation"].extendsFrom(configurations.testImplementation.get())
configurations["intTestRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())
