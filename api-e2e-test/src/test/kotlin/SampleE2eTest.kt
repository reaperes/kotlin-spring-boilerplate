
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class SampleE2eTest: StringSpec({
  FuelManager.instance.basePath = "http://localhost:8080"

  "/api/v1/helo should work" {
    val (_, response, _) = Fuel.get(
      "/api/v1/helo",
      listOf("param1" to "abc"),
    ).response()

    response.statusCode shouldBe 200
  }
})
