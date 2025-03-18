package functions

object FirstClassFunctions:

  val a = List(1, 2, 3).map(i => i * 2)
  val b = List(1, 2, 3).map(_ * 2)

  val testList = List(1, 2, 3)
  val testList2 = List(4, 5, 6)
  val productFromList = testList.foldLeft[Int](0)(_ * _)
