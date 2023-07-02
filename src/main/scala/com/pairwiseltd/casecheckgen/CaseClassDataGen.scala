package com.pairwiseltd.casecheckgen

import com.pairwiseltd.casecheckgen.utils.TypeTagUtils._
import org.scalacheck.Gen

import scala.reflect.runtime.universe
import scala.util.{Failure, Success, Try}
import scala.reflect.runtime.universe._

object CaseClassDataGen {
  private val mirror = runtimeMirror(getClass.getClassLoader)

  def apply[T](implicit tag: TypeTag[T]): Gen[T] = {
    typeOf[T] match {
      case t if t =:= typeOf[Int] => Gen.chooseNum(Int.MinValue, Int.MaxValue).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[Byte] => Gen.chooseNum(Byte.MinValue, Byte.MaxValue).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[Short] => Gen.chooseNum(Short.MinValue, Short.MaxValue).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[Long] => Gen.chooseNum(Long.MinValue, Long.MaxValue).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[Boolean] => Gen.oneOf(true, false).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[Float] => Gen.chooseNum(Float.MinValue, Float.MaxValue).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[Double] => Gen.chooseNum(Double.MinValue, Double.MaxValue).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[String] => Gen.alphaNumStr.asInstanceOf[Gen[T]]
      case t if t =:= typeOf[Option[Int]] => Gen.option(Gen.chooseNum(Int.MinValue, Int.MaxValue)).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[Option[Byte]] => Gen.option(Gen.chooseNum(Byte.MinValue, Byte.MaxValue)).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[Option[Short]] => Gen.option(Gen.chooseNum(Short.MinValue, Short.MaxValue)).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[Option[Long]] => Gen.option(Gen.chooseNum(Long.MinValue, Long.MaxValue)).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[Option[Boolean]] => Gen.option(Gen.oneOf(true, false)).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[Option[Float]] => Gen.option(Gen.chooseNum(Float.MinValue, Float.MaxValue)).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[Option[Double]] => Gen.option(Gen.chooseNum(Double.MinValue, Double.MaxValue)).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[Option[String]] => Gen.option(Gen.alphaNumStr).asInstanceOf[Gen[T]]
      case t if t.erasure =:= typeOf[Option[Any]] =>
        Gen.option(CaseClassDataGen(t.typeArgs.head.asTypeTag)).asInstanceOf[Gen[T]]
      case _ =>
        Try {
          val method = typeOf[T].companion.decl(TermName("apply")).asMethod
          val params = method.paramLists.head

          val args = params.map { param =>
            val typeSignature = param.typeSignature
            param.info match {
              case t if t =:= typeOf[Int] => CaseClassDataGen[Int]
              case t if t =:= typeOf[Byte] => CaseClassDataGen[Byte]
              case t if t =:= typeOf[Short] => CaseClassDataGen[Short]
              case t if t =:= typeOf[Long] => CaseClassDataGen[Long]
              case t if t =:= typeOf[Boolean] => CaseClassDataGen[Boolean]
              case t if t =:= typeOf[Float] => CaseClassDataGen[Float]
              case t if t =:= typeOf[Double] => CaseClassDataGen[Double]
              case t if t =:= typeOf[String] => CaseClassDataGen[String]
              case t if t =:= typeOf[Option[Int]] => Gen.option(CaseClassDataGen[Int])
              case t if t =:= typeOf[Option[Byte]] => Gen.option(CaseClassDataGen[Byte])
              case t if t =:= typeOf[Option[Short]] => Gen.option(CaseClassDataGen[Short])
              case t if t =:= typeOf[Option[Long]] => Gen.option(CaseClassDataGen[Long])
              case t if t =:= typeOf[Option[Boolean]] => Gen.option(CaseClassDataGen[Boolean])
              case t if t =:= typeOf[Option[Float]] => Gen.option(CaseClassDataGen[Float])
              case t if t =:= typeOf[Option[Double]] => Gen.option(CaseClassDataGen[Double])
              case t if t =:= typeOf[Option[String]] => Gen.option(CaseClassDataGen[String])
              case t if t.erasure =:= typeOf[Option[Any]] =>
                Gen.option(CaseClassDataGen(param.info.typeArgs.head.asTypeTag))
              case t if typeSignature.typeSymbol.isClass
                && typeSignature.typeSymbol.asClass.isCaseClass => CaseClassDataGen(param.asTypeTag)
              case t =>
                println(t)
                println(t.erasure)
                throw new IllegalArgumentException(s"doesn't support generating $t")
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
}
