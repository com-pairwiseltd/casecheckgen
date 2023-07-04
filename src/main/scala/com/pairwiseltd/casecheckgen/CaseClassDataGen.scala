package com.pairwiseltd.casecheckgen

import com.pairwiseltd.casecheckgen.utils.TypeTagUtils._
import org.scalacheck.Gen

import java.sql.{Date, Timestamp}
import java.time.{Instant, LocalDate, LocalDateTime, OffsetDateTime, ZoneId, ZoneOffset, ZonedDateTime}
import scala.reflect.runtime.universe._
import scala.util.{Failure, Success, Try}

object CaseClassDataGen {
  private val mirror = runtimeMirror(getClass.getClassLoader)

  def apply[T](implicit tag: TypeTag[T]): Gen[T] = {
    val dateRangeGen = Gen.chooseNum(LocalDate.of(1970, 1, 1).toEpochDay, LocalDate.now.toEpochDay)
    val dateTimeRangeGen = Gen.chooseNum(0, Instant.now.getEpochSecond)
    typeOf[T] match {
      case t if t =:= typeOf[Int] => Gen.chooseNum(Int.MinValue, Int.MaxValue).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[Byte] => Gen.chooseNum(Byte.MinValue, Byte.MaxValue).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[Short] => Gen.chooseNum(Short.MinValue, Short.MaxValue).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[Long] => Gen.chooseNum(Long.MinValue, Long.MaxValue).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[Boolean] => Gen.oneOf(true, false).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[Float] => Gen.chooseNum(Float.MinValue, Float.MaxValue).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[Double] => Gen.chooseNum(Double.MinValue, Double.MaxValue).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[BigDecimal] => Gen.chooseNum(Double.MinValue, Double.MaxValue).map(BigDecimal.apply).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[String] => Gen.alphaNumStr.asInstanceOf[Gen[T]]
      case t if t =:= typeOf[Timestamp] => dateTimeRangeGen
        .map(epochSecond => Timestamp.from(Instant.ofEpochSecond(epochSecond))).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[Date] => dateRangeGen
        .map(days => new Date(LocalDate.ofEpochDay(days).toEpochDay)).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[LocalDate] => dateRangeGen
        .map(days => LocalDate.ofEpochDay(days)).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[LocalDateTime] => dateTimeRangeGen
        .map(epochSecond => LocalDateTime.ofEpochSecond(epochSecond, 0, ZoneOffset.UTC)).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[OffsetDateTime] => dateTimeRangeGen
        .map(epochSecond => OffsetDateTime.ofInstant(Instant.ofEpochSecond(epochSecond), ZoneId.of("UTC"))).asInstanceOf[Gen[T]]
      case t if t =:= typeOf[ZonedDateTime] => dateTimeRangeGen
        .map(epochSecond => ZonedDateTime.ofInstant(Instant.ofEpochSecond(epochSecond), ZoneId.of("UTC"))).asInstanceOf[Gen[T]]
      case t if t.erasure =:= typeOf[Option[Any]] =>
        Gen.option(CaseClassDataGen(t.typeArgs.head.asTypeTag)).asInstanceOf[Gen[T]]
      case t if t.erasure =:= typeOf[Seq[Any]] || t.erasure =:= typeOf[List[Any]] =>
        Gen.listOfN(3, CaseClassDataGen(t.typeArgs.head.asTypeTag))
          .asInstanceOf[Gen[T]]
      case t if t.erasure =:= typeOf[Set[_]] =>
        Gen.listOfN(3, CaseClassDataGen(t.typeArgs.head.asTypeTag)).map(_.toSet).asInstanceOf[Gen[T]]
      case t if t.erasure =:= typeOf[Map[_, Any]] =>
        Gen.mapOfN(3, Gen.zip(
          CaseClassDataGen(t.typeArgs.head.asTypeTag),
          CaseClassDataGen(t.typeArgs.last.asTypeTag))).asInstanceOf[Gen[T]]
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
              case t if t =:= typeOf[BigDecimal] => CaseClassDataGen[BigDecimal]
              case t if t =:= typeOf[String] => CaseClassDataGen[String]
              case t if t =:= typeOf[Timestamp] => CaseClassDataGen[Timestamp]
              case t if t =:= typeOf[Date] => CaseClassDataGen[Date]
              case t if t =:= typeOf[LocalDate] => CaseClassDataGen[LocalDate]
              case t if t =:= typeOf[LocalDateTime] => CaseClassDataGen[LocalDateTime]
              case t if t =:= typeOf[OffsetDateTime] => CaseClassDataGen[OffsetDateTime]
              case t if t =:= typeOf[ZonedDateTime] => CaseClassDataGen[ZonedDateTime]
              case t if t.erasure =:= typeOf[Option[Any]] =>
                Gen.option(CaseClassDataGen(param.info.typeArgs.head.asTypeTag))
              case t if t.erasure =:= typeOf[Seq[Any]] || t.erasure =:= typeOf[List[Any]] =>
                Gen.listOfN(3, CaseClassDataGen(param.info.typeArgs.head.asTypeTag))
              case t if t.erasure =:= typeOf[Set[_]] =>
                Gen.listOfN(3, CaseClassDataGen(param.info.typeArgs.head.asTypeTag)).map(_.toSet)
              case t if t.erasure =:= typeOf[Map[_, Any]] =>
                Gen.mapOfN(3, Gen.zip(
                  CaseClassDataGen(param.info.typeArgs.head.asTypeTag),
                  CaseClassDataGen(param.info.typeArgs.last.asTypeTag)))
              case _ if typeSignature.typeSymbol.isClass
                && typeSignature.typeSymbol.asClass.isCaseClass => CaseClassDataGen(param.asTypeTag)
              case t =>
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
