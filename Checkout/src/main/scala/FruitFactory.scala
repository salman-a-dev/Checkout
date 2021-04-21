object FruitFactory {

  def generateFruit(ls: List[String]): Seq[Either[FruitError, Fruit]] = {
    val listOfFruits = ls.map(
      fruit => fruit.toLowerCase match {
        case "apple" => Right(Apple())
        case "orange" => Right(Orange())
        case unknownFruit => Left(FruitNotFound(unknownFruit))
      }
    )

    listOfFruits
  }
}