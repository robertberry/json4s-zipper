package com.gu.json

import org.json4s.JsonAST._
import scalaz._


object Lenses {

  def field(name: String): JValue @?> JValue = mkPLens(_.field(name))

  def elem(index: Int): JValue @?> JValue = mkPLens(_.elem(index))

  def strVal: JValue @?> String = mkPLensP {
    case JString(s) => Store(JString.apply, s)
  }

  def intVal: JValue @?> BigInt = mkPLensP {
    case JInt(i) => Store(JInt.apply, i)
  }

  def doubleVal: JValue @?> Double = mkPLensP {
    case JDouble(d) => Store(JDouble.apply, d)
  }

  def decimalVal: JValue @?> BigDecimal = mkPLensP {
    case JDecimal(d) => Store(JDecimal.apply, d)
  }

  def boolVal: JValue @?> Boolean = mkPLensP {
    case JBool(b) => Store(JBool.apply, b)
  }

  def mkPLensP[A](pfn: PartialFunction[JValue, Store[A, JValue]]): JValue @?> A =
    PLens(pfn.lift)

  def mkPLens(f: JCursor => Option[JCursor]): JValue @?> JValue =
    PLens { jValue =>
      for (c <- f(JCursor.fromJValue(jValue))) yield Store(
        newFocus => c.replace(newFocus).toJValue,
        c.focus
      )
    }
}
