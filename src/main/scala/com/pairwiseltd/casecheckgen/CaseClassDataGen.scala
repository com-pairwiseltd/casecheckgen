package com.pairwiseltd.casecheckgen

import com.pairwiseltd.casecheckgen.utils.TypeTagUtils._
import org.scalacheck.Gen

import scala.util.{Failure, Success, Try}
import scala.reflect.runtime.universe._

object CaseClassDataGen {
  private val mirror = runtimeMirror(getClass.getClassLoader)

  def apply[T](implicit tag: TypeTag[T]): Gen[T] = {
    Try {
      val method = typeOf[T].companion.decl(TermName("apply")).asMethod
      val params = method.paramLists.head

      val args = params.map { param =>
        val typeSignature = param.typeSignature
        param.info match {
          case t if t =:= typeOf[Int] => Gen.chooseNum(Int.MinValue, Int.MaxValue)
          case t if t =:= typeOf[Byte] => Gen.chooseNum(Byte.MinValue, Byte.MaxValue)
          case t if t =:= typeOf[Short] => Gen.chooseNum(Short.MinValue, Short.MaxValue)
          case t if t =:= typeOf[Long] => Gen.chooseNum(Long.MinValue, Long.MaxValue)
          case t if t =:= typeOf[Boolean] => Gen.oneOf(true, false)
          case t if t =:= typeOf[Float] => Gen.chooseNum(Float.MinValue, Float.MaxValue)
          case t if t =:= typeOf[Double] => Gen.chooseNum(Double.MinValue, Double.MaxValue)
          case t if t =:= typeOf[String] => Gen.alphaNumStr
          case t if typeSignature.typeSymbol.isClass
            && typeSignature.typeSymbol.asClass.isCaseClass => CaseClassDataGen(param.asTypeTag)
          case t => throw new IllegalArgumentException(s"doesn't support generating $t")
        }
      }
      val invertedArgs = Gen.sequence[List[Any], Any](args)
      invertedArgs.map { listOfArgs =>
        val obj = mirror.reflectModule(typeOf[T].typeSymbol.companion.asModule).instance
        mirror.reflect(obj).reflectMethod(method)(listOfArgs: _*).asInstanceOf[T]
      }
    } match {
      case Failure(exception) =>
        throw new IllegalArgumentException(s"doesn't support generating given TypeTag", exception)
      case Success(value) => value
    }
  }
}
