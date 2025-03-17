object ExtensionMethods {

  /** Extension method are the replacement for scala 2 implicits any data type
    * can be extended using extension block to add new methods and
    * functionalities
    */

  extension (s: String)
    private def makeInt(radix: Int): Int = Integer.parseInt(s, radix)

  val res1: Int = "1".makeInt(2)
  val res2: Int = "10".makeInt(2)
  val res3: Int = "100".makeInt(2)

}
