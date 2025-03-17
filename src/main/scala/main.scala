

// Main sample 1
//@main
//def main(): Unit = {
//  println("Hello world!")
//}

// another main sample
def hello(): Unit = println("Hello World..!")

// reading input from console
import scala.io.StdIn.readLine

@main def helloInteractive(): Unit =
  println("Please enter your name..!")
  val name = readLine()

  println(s"Hello, $name !")