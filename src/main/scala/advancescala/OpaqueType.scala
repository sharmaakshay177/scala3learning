package advancescala

// Opaque type aliases provide type abstraction without any overhead

class Logarithm(protected val underlying: Double):
  def toDouble: Double = math.exp(underlying)

  def +(that: Logarithm): Logarithm =
    // here we use the apply method on the companion
    Logarithm(this.toDouble + that.toDouble)

  def *(that: Logarithm): Logarithm =
    new Logarithm(this.underlying + that.underlying)

object Logarithm:
  def apply(d: Double): Logarithm = new Logarithm(math.log(d))

/** While the class Logarithm offers a nice abstraction for Double values that
  * are stored in this particular logarithmic form, it imposes severe
  * performance overhead: For every single mathematical operation, we need to
  * extract the underlying value and then wrap it again in a new instance of
  * Logarithm.
  */

// lets try module abstractions
trait Logarithms:
  type Logarithm
  // operations
  def add(x: Logarithm, y: Logarithm): Logarithm
  def mul(x: Logarithm, y: Logarithm): Logarithm
  // conversion functions
  def make(d: Double): Logarithm
  def extract(x: Logarithm): Double
  // extension methods
  extension (x: Logarithm)
    def toDouble: Double = extract(x)
    def +(y: Logarithm) = add(x, y)
    def *(y: Logarithm) = mul(x, y)

// lets implement  that
object LogarithmsImpl extends Logarithms:
  override type Logarithm = Double

  override def add(x: Logarithm, y: Logarithm): Logarithm = make(x + y)

  override def mul(x: Logarithm, y: Logarithm): Logarithm = x + y

  override def make(d: Double): Logarithm = math.log(d)

  override def extract(x: Logarithm): Double = math.exp(x)

// Within the implementation of LogarithmsImpl, the equation Logarithm = Double allows us to implement the various methods.
// beware it is a leaky implementation
/** We have to make sure to only ever program against the abstract interface
  * Logarithms and never directly use LogarithmsImpl. Directly using
  * LogarithmsImpl would make the equality Logarithm = Double visible for the
  * user, who might accidentally use a Double where a logarithmic double is
  * expected. For example:
  */

// boxing overhead
/** Type abstractions, such as type Logarithm erase to their bound (which is Any
  * in our case). That is, although we do not need to manually wrap and unwrap
  * the Double value, there will be still some boxing overhead related to boxing
  * the primitive type Double.
  */

object OpaqueType {

  object Logarithms:
    opaque type Logarithm = Double

    object Logarithm:
      def apply(d: Double): Logarithm = math.log(d)
    end Logarithm

    extension (x: Logarithm)
      def toDouble: Double = math.exp(x)
      def + (y: Logarithm): Logarithm = Logarithm(math.exp(x) + math.exp(y))
      def * (y: Logarithm): Logarithm = x + y

}
