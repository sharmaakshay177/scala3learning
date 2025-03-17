class Circle(radius: Double):
  import Circle.*
  def area: Double = calculateArea(radius)

object Circle:
  private def calculateArea(radius: Double): Double =
    scala.math.Pi * scala.math.pow(radius, 2.0)


// creating modules
trait AddService:
  def add(a: Int, b: Int): Int = a + b

trait MultiplyService:
  def multiply(a: Int, b: Int): Int = a * b

object MathService extends AddService, MultiplyService



@main def tester: Unit =
  val circle = Circle(5.0)
  println(circle.area)
  import MathService.*
  println(add(1, 2))
  println(multiply(2, 2))
