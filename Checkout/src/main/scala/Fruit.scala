sealed abstract class Fruit(val price:Int)


final case class Apple(override val price: Int = 60) extends Fruit(price)

final case class Orange(override val price: Int = 25) extends Fruit(price)

object Apple extends Offer {
  override  def specialOffer(fruitList: List[Fruit]) : Double = {


    val priceOff = (numberOfApples:Int, price:Int)=>{
      ((numberOfApples / 2) * {
      if (numberOfApples == 0) 0 else price
    }) / 100.0}
    applyOffer(fruitList, Apple(), priceOff)


  }

}

object Orange extends Offer {
  override  def specialOffer(fruitList: List[Fruit]) : Double = {

    val priceOff = (numberOfOranges:Int, price:Int) =>{((numberOfOranges / 3) * {
      if (numberOfOranges == 0) 0 else price
    }) / 100.0}

    applyOffer(fruitList, Orange(), priceOff)
  }
}


sealed trait Offer {
  def applyOffer(fruitList: List[Fruit], fruit: Fruit, calculatePriceOff: (Int, Int) => Double): Double = {
    val specificFruitList = fruitList.filter(_.getClass == fruit.getClass)

    calculatePriceOff(specificFruitList.length, specificFruitList.head.price)
  }

  def specialOffer(fruit: List[Fruit]) : Double


}