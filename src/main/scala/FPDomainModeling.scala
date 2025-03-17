enum CrustSize:
  case Small, Medium, Large

enum CrustType:
  case Thin, Thick, Regular

enum Topping:
  case Cheese, Pepperoni, BlackOlives, GreenOlives, Onions

case class Pizza(
    curstSize: CrustSize,
    crustType: CrustType,
    topping: Topping
)

// enum with params
enum HttpResponse(val code: Long):
  case Ok extends HttpResponse(200)
  case MovedPermanently extends HttpResponse(301)
  case FileNotFound extends HttpResponse(404)

// enum with members
enum Planet(private val mass: Double, val radius: Double):
  // fields
  private final val G = 6.67300e-11
  // methods
  private def surfaceGravity: Double = G * mass / (radius * radius)
  def surfaceWeight(otherMass: Double): Double = otherMass * surfaceGravity
  // cases
  case Mercury extends Planet(3.303e+23, 2.4397e6)
  case Earth extends Planet(5.976e+24, 6.37814e6)

// another variant for enum
enum Nat:
  case Zero
  case Succ(pred: Nat)

object FPDomainModeling {
  import CrustSize._
  val currentCrustSize: CrustSize = Small

  import Planet.*
//  val earthMass = Earth.mass not allowed since type is private
  val earthRadius: Double = Earth.radius
//  val earthSurfaceGravity = Earth.surfaceGravity, not allowed since type is private
  val earthSurfaceWeight: Double = Earth.surfaceWeight(Mercury.radius)

}
