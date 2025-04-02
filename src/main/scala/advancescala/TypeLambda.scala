package advancescala

import scala.reflect.ClassTag

object TypeLambda {

  /** A type lambda lets one express a higher-kinded type directly, without a
    * type definition.
    */

  // [X, Y] =>> Map[Y, X]
  /** Type ::= ... | TypeParamClause ‘=>>’
    *
    * Type TypeParamClause ::= ‘[’ TypeParam {‘,’ TypeParam} ‘]’
    *
    * TypeParam ::= {Annotation} (id [HkTypeParamClause] | ‘_’) TypeBounds
    *
    * TypeBounds ::= [‘>:’ Type] [‘<:’ Type]
    */

  def acceptBoundedParams[A >: Nothing <: AnyVal](a: A) =
    println(a)

  // acceptable since Boolean is sub type of Nothing
  acceptBoundedParams[Boolean](true)
  // it won't accept any since upper bound for type is only AnyVal
  // acceptBoundedParams[Any](10)
  // it won't accept Null since upper bound for type is only AnyVal and for Null it should be any
  // acceptBoundedParams[Null](null)

  // type T[X] = R
  type Option[Int] = String

  type Elem[X] = X match
    case String => Char

  trait Entry:
    type Key
    val key: Key

  def extractKey(e: Entry): e.Key = e.key

  val f1 =
    new Function1[Entry, Entry#Key]{
      override def apply(v1: Entry): Entry#Key = v1.key
    }

}
