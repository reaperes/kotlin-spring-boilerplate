dependencies {
  val kotestVersion = "4.4.3"

  testImplementation("com.appmattus.fixture:fixture:1.1.0")
  testImplementation("com.github.kittinunf.fuel:fuel:2.3.1")
  testImplementation("com.ninja-squad:springmockk:2.0.3")
  testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
  testImplementation("io.kotest:kotest-extensions-spring:$kotestVersion")
  testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
  testImplementation("io.mockk:mockk:1.11.0")
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks.named("check") {
  dependsOn(":ktlintCheck")
}
