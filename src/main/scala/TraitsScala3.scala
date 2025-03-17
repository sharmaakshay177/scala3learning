// scala 2 style
trait HasTail:
  def startTail: String = "Tail Started"
  def stopTail: String = "Tail Stopped"

// scala 3
trait Pet(name: String):
  def petName: String = name

// with keyword is not used while mixing in with other traits
class Dog(name: String) extends Pet(name), HasTail

object TraitsScala3 {
  // Functions and methods
  // it its inside class and trait then its called methods
  // otherwise its called a function

  // feature of function
  // it can take default values
  // Advance features
  // feature known as givens
  // function can have multiple input params group

}
