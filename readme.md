# Scala 3 - Main Highlights

## 1. Extension method to replace implicit
   ```
   extension(s: AnyType):
     def anyMethod: ReturnType = logic here
   ```

## 2. Adopted python like syntax
   `{}` to be removed and used only `:` but we can still use it

## 3. Enum are defined in scala 3 to be used
   Simple enum syntax
   ```
   Enum EnumName:
   case enum1, enum2
   ```

   Alternate with Additional enum features
   ```
   enum Color(val rgb: Int):
   case Red extends Color(0xFF0000)
   case Green extends Color(0x00FF00)
   case Blue extends Color(0x0000FF)
   ```
   
   Parameterized enum can have their own private variables as well and general logic
   ```
   enum Planet(mass: Double, radius: Double):
   private final val G = 6.67300E-11
   def surfaceGravity = G * mass / (radius * radius)
   def surfaceWeight(otherMass: Double) = otherMass * surfaceGravity

   case Mercury extends Planet(3.303e+23, 2.4397e6)
   case Earth   extends Planet(5.976e+24, 6.37814e6)
   ```

   companion object can be defined for enum as well
   ```
   object Planet:
   def main(args: Array[String]) =
      val earthWeight = args(0).toDouble
      val mass = earthWeight / Earth.surfaceGravity
      for (p <- values)
         println(s"Your weight on $p is ${p.surfaceWeight(mass)}")
   ```

## 4. syntax relate to if has changed a bit
   `if condition then logic1 else logic2`
   ```
   if condition1 then logic1
   else if condition2 then logic2
   else logic3
   ```

## 5. Classes earlier used to be initialized by using new keyword
   `Earlier: val obj1 = new Class1()`
   `Now: val obj1 = Class1()`
   with universal apply method now we can omit `new` keyword altogether

## 6. Trait parameters, in scala3 traits can now have parameters
   ```
   trait Pet(name: String):
      def greeting: String
      def age: Int

   class Dog(name: String, var age: Int) extends Pet(name):
      val greeting = "Woof"
   ```

   Traits are more flexible to compose—you can mix in multiple traits,
   but only extend one class—and should be preferred to classes and abstract classes most of the time.
   The rule of thumb is to use classes whenever you want to create instances of a particular type,
   and traits when you want to decompose and reuse behaviour.

   service oriented component model and view:
   abstract members
   concrete members

   abstract members can contains
   - **methods** -> def m(): T
   - **value definitions** -> val X: T
   - **type members** -> type T
   - **New** -> abstract givens -> given t: T


## 7. Open classes
   in scala 3 extending non-abstract classes in other files is restricted,
   In order to allow this, the base class needs to be marked as open

   `open class Person(name: String)`

## 8. Union types
   can expect multiple values and return multiple values using union type
   ```
   def isTruthly(a: Boolean | Int | String): Boolean
   def dogCatOrWhatever(): Dog | Plant | Car | Sun = ???
   ```
   As the example suggests, when using union types, the types don’t have to share a common hierarchy,
   and you can still accept them as arguments or return them from a method.
   ```
   case class Username(name: String)
   case class Password(hash: Hash)

   def help(id: Username | Password) =
      val user = id match
      case Username(name) => lookupName(name)
      case Password(hash) => lookupPassword(hash)
   ```

   Union types are duals of intersection types. And like & with intersection types,
   `|` is also commutative: `A | B` is the same type as `B | A`.

## 9. New -> Intersection type can be used with &
   type would be A & B, represent values that are both of type A and of the type B at
   the same time.
   ```
   trait Resettable:
      def reset(): Unit
   
   trait Growable[A]:
      def add(a: A): Unit
   
   def f(x: Resettable & Growable[String]): Unit =
      x.reset()
      x.add("first")
   ```

   & is commutative: A & B is the same type as B & A.

## 10. ADTs and GADTs
   enums can be used to create ADTs
   above notation for enumerations is very concise and
   serves as the perfect starting point for modeling your data types.
   Since we can always be more explicit,
   it is also possible to express types that are much more powerful:
   generalized algebraic datatypes (GADTs).

## 11. Variance
   Example of an invariant type
   ```
   trait Pipeline[T]:
       def process(t: T): T
   ```

   Example of a covariant type
   ```
   trait Producer[+T]:
      def make: T
   ```

   Example of a contravariant type
   ```
   trait Consumer[-T]:
      def take(t: T): Unit
   ```

## 12. Dependent method types
   A dependent function type describes function types,
   where the result type may depend on the function’s parameter values.
   The concept of dependent types, and of dependent function types is more advanced and
   you would typically only come across it when designing your own libraries or using advanced libraries.

## References
 - https://docs.scala-lang.org/scala3/book/types-variance.html
 - https://docs.scala-lang.org/scala3/book/types-opaque-types.html
 - https://docs.scala-lang.org/scala3/reference/index.html

To Continue from : https://docs.scala-lang.org/scala3/book/types-introduction.html#
