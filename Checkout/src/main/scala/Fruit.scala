sealed trait Fruit

final case class Apple(price: Int = 60) extends Fruit

final case class Orange(price: Int = 25) extends Fruit

object Apple extends Offer[Apple] {
  override def applyOffer(fruitList: List[Fruit]): List[Apple] = {
    val apples = fruitList.flatMap {
      case apple: Apple => Some(apple)
      case _ => None
    }
    val numberOfApplesAfterOffer = (apples.length / 2) + (apples.length % 2)
    apples.slice(0, numberOfApplesAfterOffer)
  }
}

object Orange extends Offer[Orange] {
  override def applyOffer(fruitList: List[Fruit]): List[Orange] = {
    val oranges = fruitList.flatMap {
      case orange: Orange => Some(orange)
      case _ => None
    }
    val numberOfOrangesAfterOffer = oranges.length - (oranges.length / 3)
    oranges.slice(0, numberOfOrangesAfterOffer)
  }
}


sealed trait Offer[A] {
  def applyOffer(fruitList: List[Fruit]): List[A]
}