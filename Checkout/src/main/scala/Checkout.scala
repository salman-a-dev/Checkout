object Checkout extends App{

  val listOfFruits = List("Apple", "Apple", "Orange", "Orange", "Orange", "Orange", "Orange", "Orange", "Orange")

  def displayResult(): Unit = {
    processCheckout(listOfFruits).foreach(result => println(result))
  }

  def processCheckout(ls: List[String]): List[String] = {

    val fruitContainer = FruitFactory.generateFruit(ls)
    val fruitListErrorContainer = if (fruitContainer.isEmpty) List(Left(EmptyFruitList)) else fruitContainer.filter(_.isLeft)

    if (fruitListErrorContainer.nonEmpty) {
      fruitListErrorContainer.map {
        case Left(FruitNotFound(str)) => s"Fruit: $str could not be found."
        case Left(EmptyFruitList) => "Error: The fruit list is empty."
      }


    }.toList else {
      val ls = fruitContainer.flatMap(_.toOption)
      val res = calculatePrice(ls.toList)
      val finalPriceString = f"£$res%1.2f"
      List(finalPriceString)

    }

  }

  private def calculatePrice(fruitContainer: List[Fruit]): Float = {
    val totalPrice = fruitContainer.foldLeft(0) {
      (total, next) => {
        next match {
          case Apple(price) => total + price
          case Orange(price) => total + price
        }
      }
    }.toFloat / 100
    totalPrice

  }



}
