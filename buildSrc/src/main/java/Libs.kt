object Libs {
  object Boms {
    const val kotlinBom = "org.jetbrains.kotlin:kotlin-bom:1.6.10"
  }

  object Swagger {
    private const val SWAGGER_VER = "3.0.0"

    const val starter = "io.springfox:springfox-boot-starter:$SWAGGER_VER"
    const val swagger2 = "io.springfox:springfox-swagger2:$SWAGGER_VER"
    const val swaggerUi = "io.springfox:springfox-swagger-ui:$SWAGGER_VER"
  }

  object MapStruct {
    private const val MAPSTRUCT_VER = "1.4.2.Final"

    const val mapstruct = "org.mapstruct:mapstruct:$MAPSTRUCT_VER"
    const val mapstructProcessor = "org.mapstruct:mapstruct-processor:$MAPSTRUCT_VER"
  }

  object SpringBoot {
    const val starterWeb = "org.springframework.boot:spring-boot-starter-web"
    const val starterActuator = "org.springframework.boot:spring-boot-starter-actuator"
    const val starterDataJpa = "org.springframework.boot:spring-boot-starter-data-jpa"
  }

  object Kotest {
    private const val KOTEST_VER = "5.1.0"

    const val runnerJunit5 = "io.kotest:kotest-runner-junit5:$KOTEST_VER"
    const val assertionsCore = "io.kotest:kotest-assertions-core:$KOTEST_VER"
  }

  const val jacksonModuleKotlin = "com.fasterxml.jackson.module:jackson-module-kotlin"
  const val flywayCore = "org.flywaydb:flyway-core:5.2.4"  // free version
  const val mysqlConnectorJava = "mysql:mysql-connector-java:8.0.28"
  const val fixture = "com.appmattus.fixture:fixture:1.2.0"
  const val mockk = "io.mockk:mockk:1.12.2"
  const val springMockk = "com.ninja-squad:springmockk:3.1.0"
}
