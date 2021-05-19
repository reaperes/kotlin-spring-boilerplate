rootProject.name = "kotlin-spring-boilerplate"

pluginManagement {
  repositories {
    gradlePluginPortal()
  }
}

include(
  "api",
  "api-e2e-test",
)
