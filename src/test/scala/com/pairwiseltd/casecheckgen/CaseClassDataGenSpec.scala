package com.pairwiseltd.casecheckgen

import com.pairwiseltd.casecheckgen.model.{NestedCaseClass, SimpleCaseClass, SimpleCaseClassWithCollectionHKT, SimpleCaseClassWithHigherKindedType, SimpleClass}
import org.scalacheck.Prop.forAll
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.Checkers

class CaseClassDataGenSpec extends AnyWordSpec
  with Matchers
  with Checkers {
  "CaseClassDataGen" when {
    "called with a simple case class type" should {
      "create a simple case class generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseClassDataGen[SimpleCaseClass]) { simple =>
          simple.productArity == 8
        }
        check(property)
      }
    }
    "called with a nested simple case class type" should {
      "create a nested case class generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseClassDataGen[NestedCaseClass]) { nestedCaseClass =>
          nestedCaseClass.productArity == 1 &&
            nestedCaseClass.simple.productArity == 8
        }
        check(property)
      }
    }
    "called with a simple class type" should {
      "throw IllegalArgumentException" in {
        assertThrows[IllegalArgumentException] {
          val property = forAll(CaseClassDataGen[SimpleClass]) { _ =>
            true
          }
          check(property)
        }
      }
    }
  }
  "called with a simple case class type with higher kinded Option fields with basic types" should {
    "create a generator to be used with scalacheck forAll quantifier" in {

      val property = forAll(CaseClassDataGen[SimpleCaseClassWithHigherKindedType]) { simpleCaseClassWithHigherKindedType =>
        simpleCaseClassWithHigherKindedType.productArity == 8
      }
      check(property)
    }
  }
  "called with a simple case class type with other unsupported higher kinded fields" should {
    "throw an IllegalArgumentException" in {
      assertThrows[IllegalArgumentException] {
        val property = forAll(CaseClassDataGen[SimpleCaseClassWithCollectionHKT]) { simpleCaseClassWithCollectionHKT =>
          true
        }
        check(property)
      }
    }
  }
}
