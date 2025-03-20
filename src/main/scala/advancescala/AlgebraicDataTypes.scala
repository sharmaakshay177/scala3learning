package advancescala

// representation 1
enum Color:
  case Red, Green, Blue

// representation 2
enum Color2:
  case Red extends Color2
  case Green extends Color2
  case Blue extends Color2

// parameters in enum
enum Color3(val rgb: Int):
  case Red extends Color3(0xff0000)
  case Green extends Color3(0x00ff00)
  case Blue extends Color3(0x0000ff)

object AlgebraicDataTypes {
  // Enum concept is general enough to also support ADT's
  // and their generalized version GADT's

  // Example of option as ADT
  // enum can have their won methods as well
  enum Option[+T]:
    case Some(x: T)
    case None

    def isDefined: Boolean =
      this match
        case None => false
        case Some(t) => true

  // extend is omitted in previous example
  enum Option2[+T]:
    case Some(x: T) extends Option2[T]
    case None extends Option2[Nothing]


  object Option:
    def apply[T >: Null](x: T): Option[T] =
      if (x == null) None else Some(x)


  // combination of non parameterized and parameterized enum
  enum Color(val rgb: Int):
    case Red extends Color(0xFF0000)
    case Green extends Color(0x00FF00)
    case Blue extends Color(0x0000FF)
    case Mix(mix: Int) extends Color(mix)


  // recursive enumerations
  enum Nat:
    case Zero
    case Succ(n: Nat)

}
