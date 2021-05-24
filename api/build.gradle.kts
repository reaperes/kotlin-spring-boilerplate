plugins {
  id("org.flywaydb.flyway") version "7.9.0"
}

apply(plugin = "kotlin-spring")
apply(plugin = "kotlin-jpa")
apply(plugin = "io.spring.dependency-management")
apply(plugin = "org.springframework.boot")
apply(plugin = "org.flywaydb.flyway")

dependencies {
  val kotestVersion = "4.4.3"
  val mapstructVersion = "1.4.2.Final"
  val springfoxSwaggerVersion = "3.0.0"

  implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.4.21"))
  implementation(platform("software.amazon.awssdk:bom:2.16.60"))

  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("com.jayway.jsonpath:json-path")
  implementation("io.springfox:springfox-boot-starter:$springfoxSwaggerVersion")
  implementation("io.springfox:springfox-swagger2:$springfoxSwaggerVersion")
  implementation("io.springfox:springfox-swagger-ui:$springfoxSwaggerVersion")
  implementation("org.mapstruct:mapstruct-jdk8:$mapstructVersion")
  implementation("org.mapstruct:mapstruct:$mapstructVersion")
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("io.kubernetes:client-java:12.0.1")
  implementation("org.flywaydb:flyway-core:7.5.2")

  kapt("org.mapstruct:mapstruct-processor:$mapstructVersion")

  runtimeOnly("mysql:mysql-connector-java:8.0.23")

  testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
  testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
  testImplementation("io.kotest:kotest-extensions-spring:$kotestVersion")
  testImplementation("com.appmattus.fixture:fixture:1.1.0")
  testImplementation("io.mockk:mockk:1.11.0")
  testImplementation("com.ninja-squad:springmockk:2.0.3")
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

flyway {
  val yaml = org.yaml.snakeyaml.Yaml()
  val resourceDir = sourceSets.main.get().output.resourcesDir!!
  val appYaml = resourceDir.resolve("application.yaml")

  if (appYaml.exists()) {
    val documents = yaml.loadAll(appYaml.inputStream())
    val localProp = getDocumentByProfile("local", documents) as? Map<String, *>
    checkNotNull(localProp) { "Can not find local profile properties in application.yaml" }

    val datasourceProp = ((localProp["spring"] as? Map<String, *>)?.get("datasource") as Map<String, *>)

    url = datasourceProp["url"] as String
    user = datasourceProp["username"] as String
    password = datasourceProp["password"] as String
  }
}

fun getDocumentByProfile(profile: String, documents: Iterable<Any>): Any? {
  return documents.firstOrNull { doc ->
    val map = doc as Map<String, *>
    val docProfile = (map["spring"] as? Map<String, *>)?.get("config.activate.on-profile")
    docProfile == profile
  }
}
