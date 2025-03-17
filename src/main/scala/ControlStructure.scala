object ControlStructure {

  private val (a, b) = (10, 20)
  val simpleIfStatement: Int = if a > b then a else b

  val multiIfElseStatement: Unit =
    if 10 < 20 then println("less than")
    else if 20 < 30 then println("another less than")
    else println("not sure what it is")

  // for loop and expressions
  private val ints = List(1, 2, 3, 4, 5)

  for i <- ints do println(i)

  // for expression with guards
  for
    i <- ints
    if i > 2
  do println(i)

  val intsTwiced: List[Int] = for i <- ints yield i * 2
  val intsTwiced2: List[Int] = for (i <- ints) yield i * 2 // different style

  // try catch finally
  private def writeTextToFile(txtString: String): Unit = ???
  try writeTextToFile("sometext")
  catch case ioe: NumberFormatException => println("Got IOE")
  finally println("finally block is encountered after error")

  def getClassAsString(x: Any): String =
    x match
      case s: String => s"$s is a string"
      case i: Int    => "Int"
      case _         => "Unknown"

}
