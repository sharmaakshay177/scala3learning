import scala.util.{Failure, Success, Try}

trait ErrorHandling {
  def stringToIntWithOption(s: String): Option[Int]
  def stringToIntWithTry(s: String): Try[Int]
  def stringToIntWithEither(s: String): Either[Throwable, Int]
}

object ErrorHandling extends ErrorHandling {
  // instead of throwing exceptions we handle errors
  // and that is done using error handling data types
  // Option, Try, Either

  override def stringToIntWithOption(s: String): Option[Int] =
    try Some(s.toInt)
    catch case _: NumberFormatException => None

  override def stringToIntWithTry(s: String): Try[Int] =
    Try {
      s.toInt
    }

  // another way
  def stringToIntWithTry2(s: String): Try[Int] =
    try Success(s.toInt)
    catch case e: NumberFormatException => Failure(e)

  override def stringToIntWithEither(s: String): Either[Throwable, Int] =
    try Right(s.toInt)
    catch case e: NumberFormatException => Left(e)

}

object AdvancedWay extends ErrorHandling {
  import scala.util.control.Exception.allCatch

  override def stringToIntWithOption(s: String): Option[Int] =
    allCatch.opt(s.toInt)

  override def stringToIntWithTry(s: String): Try[Int] =
    allCatch.withTry(s.toInt)

  override def stringToIntWithEither(s: String): Either[Throwable, Int] =
    allCatch.either(s.toInt)
}
