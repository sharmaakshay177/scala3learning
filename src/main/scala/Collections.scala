object Collections extends App {

  // tuples in scala 3
  private val tuple1: (String, String, Int) = ("John", "Doe", 30)

  // scala 2 we would access first element as tuple1._1
  // Tuple elements can be accessed by using their index in Scala 3
  private val firstName: String = tuple1(0) // scala 3
  private val lastName: String = tuple1._2 // scala 2 supported in scala 3


  // to test
  println(firstName)
  println(lastName)
}
