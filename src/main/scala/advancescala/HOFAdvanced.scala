package advancescala

def sum(i: Long, j: Long): Long = i + j

def multiply(i: Long, j: Long): Long = i * j

def exec(
    a: Long,
    b: Long,
    twoParamLongToLong: (Long, Long) => Long
        ): Unit =
  println(twoParamLongToLong(a, b))


object HOFAdvanced extends App {
  exec(2,30, sum)
  exec(2,30, multiply)

}
