package functions

// HOF -> Higher Order Function
object HofAndCurriedFunctions {

  // function syntax 1
  private def sum(a: Int, b: Int): Int = a + b
  println(sum(1, 2))
  // curried function syntax
  private def sumCurried(a: Int)(b: Int): Int = a + b
  println(sumCurried(1)(2))

  // “Call By-Value” (CBV), Most of the input parameters are used by call CBV, examples as sum function above
  // Other way CBN
  // Call By Name -> CBN

  /** Good analogies to understand both by Rob Norris
    *
    * A by-value parameter is like receiving a val field; its body is evaluated
    * once, right before the parameter is bound to the function.
    *
    * A by-name parameter is like receiving a def method; its body is evaluated
    * later, whenever it’s used inside the function.
    */

  private def exec(blockOfCode: => Long): Long = ???

  /** Because => always means transform in Scala, you get the idea that param is
    * something that is transformed into an Int in this example
    */

  // so if you do something like this it will fail, uncomment to see the error
  // exec(println(1))
  exec(1L + 2L)

  // with combination of both, now we have the option to get  some thing like this
  private def evaluate(a: Long, b: Long)(
      operation: (Long, Long) => Long
  ): Long =
    operation(a, b)

  private def sum(a: Long, b: Long): Long = a + b
  // one way
  println(evaluate(10, 20)(sum))
  // another way
  println(evaluate(10, 20)(_ + _))
  println(evaluate(10, 20)(_ * _))

  // https://www.learnscala.dev/challenge-page/introduction-to-scala-3-video-course?programId=2911a5e2-c6be-4fe1-ba34-e81eb626e03d&participantId=8327b5db-35bc-4ead-898a-bfa729edcc43

}
