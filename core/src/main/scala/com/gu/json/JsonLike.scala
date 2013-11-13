package com.gu.json

/** Typeclass for a data structure representing an AST for JSON */
trait JsonLike[J] {

  def nothing: J
  def array(elems: Seq[J]): J
  def obj(fields: Seq[(String, J)]): J
  def string(s: String): J
  def int(i: BigInt): J
  def double(d: Double): J
  def bool(b: Boolean): J

  def asArray(j: J): Option[List[J]]
  def asObj(j: J): Option[List[(String, J)]]
  def asString(j: J): Option[String]
  def asInt(j: J): Option[BigInt]
  def asDouble(j: J): Option[Double]
  def asBool(j: J): Option[Boolean]

}

object JsonLike {
  def apply[J](implicit ev: JsonLike[J]): JsonLike[J] = ev

  def nothing[J : JsonLike]: J = JsonLike[J].nothing
  def array[J : JsonLike](elems: Seq[J]): J = JsonLike[J].array(elems)
  def obj[J : JsonLike](fields: Seq[(String, J)]): J = JsonLike[J].obj(fields)
  def string[J : JsonLike](s: String): J = JsonLike[J].string(s)
  def int[J : JsonLike](i: BigInt): J = JsonLike[J].int(i)
  def double[J : JsonLike](d: Double): J = JsonLike[J].double(d)
  def bool[J : JsonLike](b: Boolean): J = JsonLike[J].bool(b)

  def asArray[J : JsonLike](j: J): Option[List[J]] = JsonLike[J].asArray(j)
  def asObj[J : JsonLike](j: J): Option[List[(String, J)]] = JsonLike[J].asObj(j)
  def asString[J : JsonLike](j: J): Option[String] = JsonLike[J].asString(j)
  def asInt[J : JsonLike](j: J): Option[BigInt] = JsonLike[J].asInt(j)
  def asDouble[J : JsonLike](j: J): Option[Double] = JsonLike[J].asDouble(j)
  def asBool[J : JsonLike](j: J): Option[Boolean] = JsonLike[J].asBool(j)
}