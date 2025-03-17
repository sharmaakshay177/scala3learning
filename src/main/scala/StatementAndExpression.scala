object StatementAndExpression {

  /** Statements are used for side-effects, here we imply that we are not
    * interested in return value and type would be unit
    */
  val (a, b) = (1, 1)
  val statementExample: Unit = if a == b then println("equal")

  /** Expressions always return a result */

  // example 1
  val minExpression: Int = if a < b then a else b
  // example 2
  def makeInt(s: String): Int =
    try s.toInt
    catch case e: NumberFormatException => 0

}
