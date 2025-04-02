package advancescala

trait SemiGroup[T]:
  extension (x: T) def combine(y: T): T

trait Monoid[T] extends SemiGroup[T]:
  def unit: T

object TypeClassesDetails {

  // given instance for string
  given Monoid[String]: extension(x: String)
  def combine(y: String): String = x.concat(y)
  def unit: String = ""

  // long Monoid
  given longMonoid: Monoid[Long] = new Monoid[Long] {
    extension (x: Long) def combine(y: Long): Long = x + y
    override def unit: Long = 0L
  }

}

object IntMonoidDef {
  // given instance for int
  given Monoid[Int]: extension(x: Int)
  def combine(y: Int): Int = x + y
  def unit: Int = 11

}

object UsingMonoid {

  def combineAll[T: Monoid as m](xs: List[T]): T =
    xs.foldLeft(m.unit)(_.combine(_))

}

object Functors {
  // a functor for a type provides the ability for its values to be
  // mapped over
  // i.e apply a function that transforms inside a value while remembering its shape
  trait Functor[F[_]]:
    def map[A, B](x: F[A], f: A => B): F[B]

  // lets use functor on a map to map elements with f
  given listFunctor: Functor[List] = new Functor[List] {
    override def map[A, B](x: List[A], f: A => B): List[B] =
      x.map(f)
  }

  def assertTransformation[F[_]: Functor, A, B](
      expected: F[B],
      original: F[A],
      mapping: A => B
  ): Unit =
    assert(expected == summon[Functor[F]].map(original, mapping))

}

object Monads {

  /** Applying map in Functor[List] to a mapping function of type A => B results
    * in a List[B]. So applying it to a mapping function of type A => List[B]
    * results in a List[List[B]]. To avoid managing lists of lists, we may want
    * to "flatten" the values in a single list.
    *
    * That's where Monad comes in. A Monad for type F[_] is a Functor[F] with
    * two more operations:
    *
    * flatMap, which turns an F[A] into an F[B] when given a function of type A
    * \=> F[B], pure, which creates an F[A] from a single value A.
    */

  trait Monad[F[_]] extends Functors.Functor[F]:
    // unit value for a monad
    def pure[A](a: A): F[A]

    extension [A](x: F[A])
      def flatMap[B](f: A => F[B]): F[B]
      def map[B](f: A => B) = x.flatMap(f.andThen(pure))

  end Monad

  // Examples
  given listMonad: Monad[List] = new Monad[List]:
    override def pure[A](a: A): List[A] = List(x)

    extension [A](xs: List[A])
      override def flatMap[B](f: A => List[B]): List[B] =
        xs.flatMap(f) // using existing flat map from list

  given optionMonad: Monad[Option] = new Monad[Option]:
    override def pure[A](a: A): Option[A] = Option(a)

    extension [A](x: Option[A])
      override def flatMap[B](f: A => Option[B]): Option[B] =
        x match
          case Some(v) => f(v)
          case None    => None

  /** The definition of a type class is expressed with a parameterised type with
    * abstract members, such as a trait. The main difference between subtype
    * polymorphism and ad-hoc polymorphism with type classes is how the
    * definition of the type class is implemented, in relation to the type it
    * acts upon. In the case of a type class, its implementation for a concrete
    * type is expressed through a given instance definition, which is supplied
    * as an implicit argument alongside the value it acts upon. With subtype
    * polymorphism, the implementation is mixed into the parents of a class, and
    * only a single term is required to perform a polymorphic operation. The
    * type class solution takes more effort to set up, but is more extensible:
    * Adding a new interface to a class requires changing the source code of
    * that class. But contrast, instances for type classes can be defined
    * anywhere.
    */

}
