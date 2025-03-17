object DependentTypes {

  trait Key { type Value }

  trait DB {
    def get(k: Key): Option[k.Value] // dependent method
  }

  object Name extends Key {
    override type Value = String
  }
  object Age extends Key {
    override type Value = Long
  }

  // scala 2 way
  val db = new DB:
    override def get(k: Key): Option[k.Value] = ???

  val name: Option[String] = db.get(Name)
  val age: Option[Long] = db.get(Age)

  // scala 3 we use a lambda syntax
  //type DB2 = (k: Key) => Option[k.Value]

  trait Nums:
    // type of number left for abstraction
    type Num
    // some methods
    def lit(d: Double): Num
    def add(l: Num, r: Num): Num
    def mul(l: Num, r: Num): Num
  end Nums


}
