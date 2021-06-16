plugins {
  id("org.flywaydb.flyway") version "7.9.0"
}

apply(plugin = "kotlin-spring")
apply(plugin = "kotlin-jpa")
apply(plugin = "io.spring.dependency-management")
apply(plugin = "org.springframework.boot")
apply(plugin = "org.flywaydb.flyway")

dependencies {
  implementation(platform(Libs.Boms.kotlinBom))
  implementation(platform(Libs.Boms.awsSdkBom))

  implementation(Libs.jacksonModuleKotlin)
  implementation(Libs.jsonPath)
  implementation(Libs.Swagger.starter)
  implementation(Libs.Swagger.swagger2)
  implementation(Libs.Swagger.swaggerUi)
  implementation(Libs.MapStruct.mapstructJdk8)
  implementation(Libs.MapStruct.mapstruct)
  implementation(Libs.SpringBoot.starterActuator)
  implementation(Libs.SpringBoot.starterWeb)
  implementation(Libs.SpringBoot.starterDataJpa)
  implementation(Libs.clientJava)
  implementation(Libs.flywayCore)

  kapt(Libs.MapStruct.mapstructProcessor)

  runtimeOnly(Libs.mysqlConnectorJava)

  testImplementation(Libs.Kotest.runnerJunit5)
  testImplementation(Libs.Kotest.assertionsCore)
  testImplementation(Libs.Kotest.extensionsSpring)
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
