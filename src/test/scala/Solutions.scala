import scala.annotation.tailrec

object Solutions:

  // a1b10
  // a, 1, b, 1, 0 (n * m), where n is the size of the string, m is the size of the string to prepare
  def solution(str: String): String =
    var resultString = new StringBuilder()
    var charToFill: Char = '_'
    var size: String = ""
    for (i <- 0 until str.length)
      val currChar = str.charAt(i)
      if (currChar.isLetter)
        if (charToFill != '_' && charToFill != currChar)
          val result = List
            .fill(size.toInt)(charToFill.toString)
            .mkString("") // could lag here
          resultString.append(result)
          size = ""
        charToFill = currChar

      if (currChar.isDigit) size = size.appended(currChar)

    if (charToFill != '_' && size != "")
      val result = List.fill(size.toInt)(charToFill.toString).mkString("")
      resultString.append(result)
    resultString.toString()
  end solution

  def optimized1(str: String): String =
    case class TempStore(charToFill: Char, size: String, acc: StringBuilder) {
      def isInitialChar: Boolean = charToFill == '_'
    }

    def resultMaker(store: TempStore): String =
      val result = List.fill(store.size.toInt)(store.charToFill).mkString("")
      store.acc.append(result).toString()

//    @tailrec
//    def maker(initStore: TempStore, head: Char, tail: Array[Char]): TempStore =
//      if (head.isLetter)
//        if (!initStore.isInitialChar && initStore.charToFill != head)
//          val interimResult = resultMaker(initStore)
//          val newStore = initStore.copy(acc = initStore.acc.append(interimResult), size = "", charToFill = head)
//          maker(newStore, tail.head, tail)
//        else maker(initStore.copy(charToFill = head), tail.head, tail.tail)
//      else
//        maker(initStore.copy(size = initStore.size.appended(head)), tail.head, tail.tail)

    // todo there is bug in size
    @tailrec
    def maker(initStore: TempStore, head: Char, tail: Array[Char]): TempStore =
      if (tail.nonEmpty)
        if (head.isLetter)
          if (!initStore.isInitialChar && initStore.charToFill != head)
            val interimResult = resultMaker(initStore)
            val newStore = initStore.copy(
              acc = new StringBuilder(interimResult),
              size = "",
              charToFill = head
            )
            maker(newStore, tail.head, tail)
          else maker(initStore.copy(charToFill = head), tail.head, tail.tail)
        else
          maker(
            initStore.copy(size = initStore.size.appended(head)),
            tail.head,
            tail.tail
          )
      else initStore

    val initialStore = TempStore('_', "", new StringBuilder())
    val charArr = str.toCharArray
    val finalStore = maker(initialStore, charArr.head, charArr.tail)
    val finalResult = resultMaker(finalStore)
    finalResult
  end optimized1

  @main def starter(): Unit =
    val test1 = "a1b10"
    val expected1 = "abbbbbbbbbb"
    val actual1 = solution(test1)
    val actual1_2 = optimized1(test1)
    require(expected1 == actual1, "They should be equal")
    require(expected1 == actual1_2, "They should be equal")
    val test2 = "d3b2f5"
    val expected2 = "dddbbfffff"
    val actual2 = solution(test2)
    val test3 = "a99b15c20"
    val e1 = List.fill(99)('a').mkString("")
    val e2 = List.fill(15)('b').mkString("")
    val e3 = List.fill(20)('c').mkString("")
    val expected3 = e1 ++ e2 ++ e3
    val actual3 = solution(test3)
    require(expected2 == actual2, "They should be equal")
    println(System.currentTimeMillis())
    require(expected3 == actual3, "They should be equal")
    println(System.currentTimeMillis())
