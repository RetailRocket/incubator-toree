package com.ibm.kernel.protocol.v5.content

import org.scalatest.{FunSpec, Matchers}
import play.api.data.validation.ValidationError
import play.api.libs.json._

class KernelInfoRequestSpec extends FunSpec with Matchers {
  val kernelInfoRequestJson: JsValue = Json.parse("""
  {}
  """)

  val kernelInfoRequest: KernelInfoRequest = KernelInfoRequest()

  describe("KernelInfoRequest") {
    describe("implicit conversions") {
      it("should implicitly convert from valid json to a KernelInfoRequest instance") {
        // This is the least safe way to convert as an error is thrown if it fails
        // This is the least safe way to convert as an error is thrown if it fails
        kernelInfoRequestJson.as[KernelInfoRequest] should be (kernelInfoRequest)
      }

      it("should also work with asOpt") {
        // This is safer, but we lose the error information as it returns
        // None if the conversion fails
        val newKernelInfoRequest = kernelInfoRequestJson.asOpt[KernelInfoRequest]

        newKernelInfoRequest.get should be (kernelInfoRequest)
      }

      it("should also work with validate") {
        // This is the safest as it collects all error information (not just first error) and reports it
        val KernelInfoRequestResults = kernelInfoRequestJson.validate[KernelInfoRequest]

        KernelInfoRequestResults.fold(
          (invalid: Seq[(JsPath, Seq[ValidationError])]) => println("Failed!"),
          (valid: KernelInfoRequest) => valid
        ) should be (kernelInfoRequest)
      }

      it("should implicitly convert from a KernelInfoRequest instance to valid json") {
        Json.toJson(kernelInfoRequest) should be (kernelInfoRequestJson)
      }
    }
  }
}
