package com.pairwiseltd.casecheckgen

import org.scalacheck.Gen

import scala.reflect.runtime.universe._

object CaseClassDataGen {
  val mirror = runtimeMirror(getClass.getClassLoader)

  def apply[T: TypeTag]: Gen[T] = {
    val method = typeOf[T].companion.decl(TermName("apply")).asMethod
    val params = method.paramLists.head
    val args = params.map { param =>
      param.info match {
        case t if t =:= typeOf[Int] => Gen.chooseNum(Int.MinValue, Int.MaxValue)
        case t if t =:= typeOf[Long] => Gen.chooseNum(Long.MinValue, Long.MaxValue)
        case t if t =:= typeOf[String] => Gen.alphaNumStr
        case t if t =:= typeOf[Boolean] => Gen.oneOf(true, false)
        case t => throw new UnsupportedOperationException(s"doesn't support generating $t")
      }
    }
    val invertedArgs = Gen.sequence[List[Any], Any](args)
    invertedArgs.map{ listOfArgs =>
      val obj = mirror.reflectModule(typeOf[T].typeSymbol.companion.asModule).instance
      mirror.reflect(obj).reflectMethod(method)(listOfArgs: _*).asInstanceOf[T]
    }
  }
}
