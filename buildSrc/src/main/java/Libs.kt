object Libs {
  object Boms {
    const val kotlinBom = "org.jetbrains.kotlin:kotlin-bom:1.4.21"
    const val awsSdkBom = "software.amazon.awssdk:bom:2.16.60"
  }

  object Swagger {
    private const val SWAGGER_VER = "3.0.0"

    const val starter = "io.springfox:springfox-boot-starter:$SWAGGER_VER"
    const val swagger2 = "io.springfox:springfox-swagger2:$SWAGGER_VER"
    const val swaggerUi = "io.springfox:springfox-swagger-ui:$SWAGGER_VER"
  }

  object MapStruct {
    private const val MAPSTRUCT_VER = "1.4.2.Final"

    const val mapstructJdk8 = "org.mapstruct:mapstruct-jdk8:$MAPSTRUCT_VER"
    const val mapstruct = "org.mapstruct:mapstruct:$MAPSTRUCT_VER"
    const val mapstructProcessor = "org.mapstruct:mapstruct-processor:$MAPSTRUCT_VER"
  }

  object SpringBoot {
    const val starterWeb = "org.springframework.boot:spring-boot-starter-web"
    const val starterDataJpa = "org.springframework.boot:spring-boot-starter-data-jpa"
    const val starterActuator = "org.springframework.boot:spring-boot-starter-actuator"
    const val starterTest = "org.springframework.boot:spring-boot-starter-test"
  }

  object Kotest {
    private const val KOTEST_VER = "4.4.3"

    const val runnerJunit5 = "io.kotest:kotest-runner-junit5:$KOTEST_VER"
    const val assertionsCore = "io.kotest:kotest-assertions-core:$KOTEST_VER"
    const val extensionsSpring = "io.kotest:kotest-extensions-spring:$KOTEST_VER"
  }

  const val snakeYaml = "org.yaml:snakeyaml:1.28"
  const val jacksonModuleKotlin = "com.fasterxml.jackson.module:jackson-module-kotlin"
  const val jsonPath = "com.jayway.jsonpath:json-path"
  const val clientJava = "io.kubernetes:client-java:12.0.1"
  const val flywayCore = "org.flywaydb:flyway-core:7.5.2"
  const val mysqlConnectorJava = "mysql:mysql-connector-java:8.0.23"
  const val fixture = "com.appmattus.fixture:fixture:1.1.0"
  const val mockk = "io.mockk:mockk:1.11.0"
  const val springMockk = "com.ninja-squad:springmockk:2.0.3"
}
