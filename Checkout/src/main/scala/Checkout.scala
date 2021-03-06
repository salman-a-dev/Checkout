object Checkout extends App{

  val specialOffers = List(Apple, Orange)
  val oneSpecialOffer = List(Apple)
  //val listOfFruits = List("Apple", "Apple", "Orange", "Orange", "Orange", "Orange", "Orange", "Orange", "Orange")
  val listOfFruits = List("Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple","Apple","Apple", "Orange", "Orange", "Orange")
  displayResult()

  def displayResult(): Unit = {
    processCheckout(listOfFruits).foreach(result => println(result))

    processCheckout(listOfFruits, specialOffers).foreach(result => println(result))

    processCheckout(listOfFruits, oneSpecialOffer).foreach(result => println(result))
  }

  def processCheckout(ls: List[String], specialOffers: List[Offer] = List()): List[String] = {

    val fruitContainer = FruitFactory.generateFruit(ls)
    val fruitListErrorContainer = if (fruitContainer.isEmpty) List(Left(EmptyFruitList)) else fruitContainer.filter(_.isLeft)

    if (fruitListErrorContainer.nonEmpty) {
      fruitListErrorContainer.map {
        case Left(FruitNotFound(str)) => s"Fruit: $str could not be found."
        case Left(EmptyFruitList) => "Error: The fruit list is empty."
      }


    }.toList else {
      val ls = fruitContainer.flatMap(_.toOption)
      val res = calculatePrice(ls.toList, specialOffers)
      val finalPriceString = f"£$res%1.2f"
      List(finalPriceString)

    }

  }

  private def calculatePrice(fruitContainer: List[Fruit], specialOffers: List[Offer] = List()): Double = {

    val priceOff = getTotalPriceOff(fruitContainer, specialOffers)

    val totalPrice = fruitContainer.foldLeft(0) {
      (total, next) => {
        next match {
          case Apple(price) => total + price
          case Orange(price) => total + price
        }
      }
    }.toFloat / 100
    totalPrice - priceOff

  }

  private def getTotalPriceOff(fruitContainer:List[Fruit], specialOffers: List[Offer] = List()): Double ={

    if (specialOffers.nonEmpty)
      specialOffers.distinct.map(offer => offer.specialOffer(fruitContainer)).foldLeft(0.0){
        (total, next) =>
          total + next
      }
    else 0

  }



}
