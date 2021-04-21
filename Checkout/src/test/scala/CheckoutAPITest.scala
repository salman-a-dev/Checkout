
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

class CheckoutAPITest extends AnyFeatureSpec with GivenWhenThen {
  val validInputFruitList = List("Apple", "Orange")
  val validInputFruitList2 = List("Apple", "Apple", "Orange", "Orange", "Orange", "Orange", "Orange", "Orange", "Orange")

  val invalidInputFruitList = List("fruit1", "yyy", "zzz")
  val emptyFruitList = List.empty

  Feature("Feature 1.0: The user provides a list at checkout ") {
    Scenario("Checkout on a list of valid items") {

      Given("A list of items")
      assert(validInputFruitList.nonEmpty)

      When("checkout is processed")
      val res = Checkout.processCheckout(validInputFruitList)
      assert(res.nonEmpty)

      Then("the total cost of items should be returned")
      res.map(result => assert(result.equals("£0.85")))

    }


    Scenario("The user provides an empty list at checkout ") {
      Given("An empty list")
      assert(emptyFruitList.isEmpty)

      When("checkout is processed")
      val res = Checkout.processCheckout(emptyFruitList)
      assert(res.nonEmpty)

      Then("an error message should be returned")
      val errString = "Error: The fruit list is empty."
      res.map(result => assert(result.equals(errString)))

    }
    Scenario("The user provides a list of items that dont exist ") {
      Given("An incorrect list")
      assert(invalidInputFruitList.nonEmpty)

      When("checkout is processed")
      val res = Checkout.processCheckout(invalidInputFruitList)
      assert(res.nonEmpty)

      Then("an error message should be returned with the unknown fruit")
      val errString = s"Fruit: ${invalidInputFruitList.head} could not be found."
      val errString1 = s"Fruit: ${invalidInputFruitList(1)} could not be found."
      val errString2 = s"Fruit: ${invalidInputFruitList(2)} could not be found."

      assert(res.head.equals(errString))
      assert(res(1).equals(errString1))
      assert(res(2).equals(errString2))


    }
  }
  Feature("Feature 1.1: Get a list of fruits") {

    Scenario("Generate a fruit list") {
      Given("A valid list")
      assert(validInputFruitList.nonEmpty)

      When("a fruit list is generated")
      val res = FruitFactory.generateFruit(validInputFruitList)
      assert(res.nonEmpty)

      Then("a fruit list is generated.")
      assert(res.nonEmpty)


    }

    Scenario("Generate a list of errors") {
      Given("an invalid list")
      assert(validInputFruitList.nonEmpty)

      When("a fruit list is generated from invalid fruit")
      val res = FruitFactory.generateFruit(validInputFruitList)
      assert(res.nonEmpty)

      Then("a list of errors is generated")
      assert(res.nonEmpty)


    }
  }

}
