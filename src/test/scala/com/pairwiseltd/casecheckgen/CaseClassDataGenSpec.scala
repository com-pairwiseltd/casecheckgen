package com.pairwiseltd.casecheckgen

import com.pairwiseltd.casecheckgen.model.{NestedCaseClass, SimpleCaseClass}
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
    "called with a nested case class type" should {
      "create a simple case class generator to be used with scalacheck forAll quantifier" in {
        assertThrows[UnsupportedOperationException] {
          val property = forAll(CaseClassDataGen[NestedCaseClass]) { simple =>
            simple.productArity == 8
          }
          check(property)
        }
      }
    }
  }
}
