sealed trait FruitError

case class FruitNotFound(fruit: String) extends FruitError

object EmptyFruitList extends FruitError
