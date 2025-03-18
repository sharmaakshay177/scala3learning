import java.io.StringWriter

// top level methods
// methods which are not inside class, trait or object
def sayHello(name: String): Unit = println(s"Hello, $name")

object TopLevelMethods {

  def singleExpressionMethod(a: Int, b: Int): Int = a + b

  def multiLineExpression(t: Throwable): String =
    println(t.getMessage)
    t.getMessage

}
