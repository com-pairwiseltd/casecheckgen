package com.pairwiseltd.casecheckgen.utils

import com.pairwiseltd.casecheckgen.model.{NestedCaseClass, SimpleCaseClass, SimpleClass}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.reflect.runtime.universe._

class TypeTagUtilsSpec extends AnyWordSpec
  with Matchers {

  "SymbolWithTypeTagSupport type class" when {
    "asTypeTag attached to Symbol called on case class" should {
      "return the type tag" in {
        val method = typeOf[NestedCaseClass].companion.decl(TermName("apply")).asMethod
        val params = method.paramLists.head
        val symbol = params.head
        import TypeTagUtils._
        symbol.asTypeTag.toString shouldBe "TypeTag[com.pairwiseltd.casecheckgen.model.SimpleCaseClass]"

      }
    }
    "asTypeTag attached to Symbol called on simple class" should {
      "return the type tag" in {
        assertThrows[IllegalArgumentException] {
          val method = typeOf[SimpleCaseClass].companion.decl(TermName("apply")).asMethod
          val params = method.paramLists.head
          val symbol = params.head
          import TypeTagUtils._
          symbol.asTypeTag.toString
        }
      }
    }
  }
}
