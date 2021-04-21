sealed trait Fruit

final case class Apple(price: Int = 60) extends Fruit
final case class Orange(price: Int = 25) extends Fruit