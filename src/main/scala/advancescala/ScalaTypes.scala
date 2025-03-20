package advancescala

/** Intersection Types */
trait Resettable:
  def reset(): Unit

trait Growable[A]:
  def add(a: A): Unit

def f(x: Resettable & Growable[String]): Unit =
  x.reset()
  x.add("first")

// another approach
trait Both[A] extends Resettable, Growable[A]

def f2(x: Both[String]): Unit = ???

/** Union Types */

case class Username(name: String)
case class Password(hash: String)

def verifyUserOrPassword(x: Username | Password): Boolean =
  val isValid = x match
    case Username(user) => ??? // function here to verify
    case Password(hash) => ??? // function here to verify
  isValid

// Alternative type
trait UsernameOrPassword
case class Username2(name: String) extends UsernameOrPassword
case class Password2(hash: String) extends UsernameOrPassword

def help(id: UsernameOrPassword) =
  id match
    case Password2(hash) => ???
    case Username2(name) => ???
    case _               => ???

// Another Approach would be TaggedUnion to use enum
// Note: this way of modelling requires explicit wrapping and unwrapping
// for example username is not a subtype of UserOrPassword
enum UserOrPassword:
  case IsUserName(u: Username2)
  case IsPassword(p: Password2)

object ScalaTypes {}
