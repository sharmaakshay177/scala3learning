package advancescala

trait Item {
  def productNumber: String
}

trait Buyable extends Item {
  def price: Int
}

trait Book extends Buyable {
  def isbn: String
}

// invariant type
trait Pipeline[T]:
  def process(t: T): T

// Covariant type
trait Producer[+T]:
  def make: T

// contravariant type
trait Consumer[-T]:
  def consume(t: T): Unit

object Variance {

  /** Invariant Types By default, types like Pipeline are invariant in their
    * type argument (T in this case). This means that types like Pipeline[Item],
    * Pipeline[Buyable], and Pipeline[Book] are in no subtyping relationship to
    * each other.
    */
  def oneOf(
      p1: Pipeline[Buyable],
      p2: Pipeline[Buyable],
      b: Buyable
  ): Buyable =
    val b1 = p1.process(b)
    val b2 = p2.process(b)
    if b1.price < b2.price then b1 else b2

}
