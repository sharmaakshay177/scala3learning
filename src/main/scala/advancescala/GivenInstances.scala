package advancescala

trait Ord[T]:
  def compare(x: T, y: T): Int
  // extension method
  extension (x: T)
    def <(y: T) = compare(x, y) < 0
    def >(y: T) = compare(x, y) > 0

// using given instances
// Syntax
// given keyword
// optional name of the given instance ex. intOrd
// generic type if any along with sub or super type
// :
// return type
// ==
// then or RHS define the ordering logic

given intOrd: Ord[Int] = new Ord[Int]:
  def compare(x: Int, y: Int) =
    if x < y then -1
    else if x > y then +1
    else 0

given listOrdering[T: Ord]: Ord[List[T]] = new Ord[List[T]]:
  def compare(xs: List[T], xy: List[T]): Int =
    (xs, xy) match
      case (Nil, Nil) => 0
      case (Nil, _)   => -1
      case (_, Nil)   => +1
      case (x :: xs1, y :: xy1) =>
        val fst = summon[Ord[T]].compare(x, y)
        if fst != 0 then fst else compare(xs1, xy1)

// name can be left out as well making it anonymous givens
given Ord[Long] = new Ord[Long]:
  // overriding the abstract compare logic
  def compare(x: Long, y: Long): Int =
    if x < y then -1
    else if x > y then +1
    else 0

object GivenInstances extends App {
  // usages
  // we can use
  println(summon[Ord[Int]].compare(10, 20))
  println(summon[Ord[Long]].compare(20, 10))
  println(summon[Ord[Long]].compare(10, 10))
  println(summon[Ord[List[Int]]].compare(List(1, 2), List(3, 4)))
  println(summon[Ord[List[Int]]].compare(List(3, 4), List(1, 2)))
  println(summon[Ord[List[Int]]].compare(List(3, 4), List(3, 4)))
  println(summon[Ord[List[Int]]].compare(List(3, 4), List(2, 4)))

}

object DeferredGivens {

  // given T: deferred
  // Such givens can be implemented automatically in subclasses.
  // deferred is a new method in the scala.compiletime package,
  // which can appear only as the right hand side of a given defined in a trait.

  // Deferred givens allow a clean implementation of context bounds in traits
  trait Sorted:
    type Element
    given Ord[Element] = compiletime.deferred

  class SortedSet[A](using ord: Ord[A]) extends Sorted:
    type Element = A
    override given Ord[Element] = ord

  // you can provide abstract givens as well
  trait HasOrd[T]:
    given ord: Ord[T]

}

object StructuralGivens {

  class IntOrd extends Ord[Int]:
    override def compare(x: Int, y: Int): Int =
      if x < y then -1 else if x > y then +1 else 0

  given IntOrd() // creating instance of give
}

object UsingClauses {

  def max[T](x: T, y: T)(using ord: Ord[T]): T =
    if ord.compare(x, y) < 0 then y else x

  println(max(2, 3)(using intOrd))

  // Anonymous context params
  // sometimes we don't need context params names
  def maximum[T](xs: List[T])(using Ord[T]): T =
    xs.reduceLeft(max)

}
