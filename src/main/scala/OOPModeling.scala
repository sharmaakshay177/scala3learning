object OOPModeling {

  // traits
  trait Speaker:
    def speak(): String

  trait TailWagger:
    def startTail(): Unit = println("tail is wagging")
    def stopTail(): Unit = println("tail is stopped")

  trait Runner:
    def startRunning(): Unit = println("I'm Running")
    def stopRunning(): Unit = println("Stopped Running")

  // in scala 3 we don't need braces python style indentation works
  // in inheritance we don't use `extends` and `with` keywords
  class Dog(name: String) extends Speaker, TailWagger, Runner:
    override def speak(): String = "Woof"

}
