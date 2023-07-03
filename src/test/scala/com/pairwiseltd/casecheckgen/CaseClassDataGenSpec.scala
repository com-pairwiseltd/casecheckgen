package com.pairwiseltd.casecheckgen

import com.pairwiseltd.casecheckgen.model._
import org.scalacheck.Prop.forAll
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.Checkers

class CaseClassDataGenSpec extends AnyWordSpec
  with Matchers
  with Checkers {
  "CaseClassDataGen for simple case classes" when {
    // Simple case class
    "called with a simple case class type" should {
      "create a simple case class generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseClassDataGen[SimpleCaseClass]) { simple =>
          simple.productArity == 8
        }
        check(property)
      }
    }
    "called with a simple case class type with higher kinded Option fields with basic types" should {
      "create a generator to be used with scalacheck forAll quantifier" in {

        val property = forAll(CaseClassDataGen[SimpleCaseClassWithOptionHKT]) { simpleCaseClassWithOptionHKT =>
          simpleCaseClassWithOptionHKT.productArity == 8
        }
        check(property)
      }
    }
    "called with a simple case class type with deeper higher kinded Option fields" should {
      "create a generator to be used with scalacheck forAll quantifier" in {

        val property = forAll(CaseClassDataGen[SimpleCaseClassWithNestedOptionHKT]) { simpleCaseClassWithNestedOptionHKT =>
          simpleCaseClassWithNestedOptionHKT.productArity == 8
        }
        check(property)
      }
    }

    "called with a simple case class type with higher kinded Seq fields of basic types" should {
      "create a generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseClassDataGen[SimpleCaseClassWithSeqHKT]) { simpleCaseClassWithSeqHKT =>
          simpleCaseClassWithSeqHKT.productArity == 8
        }
        check(property)

      }
    }
    "called with a simple case class type with deep nested higher kinded Seq fields of basic types" should {
      "create a generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseClassDataGen[SimpleCaseClassWithDeepNestedSeqHKT]) { simpleCaseClassWithDeepNestedSeqHKT =>

          simpleCaseClassWithDeepNestedSeqHKT.productArity == 8
        }
        check(property)
      }
    }
    "called with a simple case class type with higher kinded List fields of basic types" should {
      "create a generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseClassDataGen[SimpleCaseClassWithListHKT]) { simpleCaseClassWithListHKT =>
          simpleCaseClassWithListHKT.productArity == 8
        }
        check(property)

      }
    }
    "called with a simple case class type with deep nested higher kinded List fields of basic types" should {
      "create a generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseClassDataGen[SimpleCaseClassWithDeepNestedListHKT]) { simpleCaseClassWithDeepNestedListHKT =>
          simpleCaseClassWithDeepNestedListHKT.productArity == 8
        }
        check(property)

      }
    }

    "called with a simple case class type with higher kinded Set fields of basic types" should {
      "create a generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseClassDataGen[SimpleCaseClassWithSetHKT]) { simpleCaseClassWithSetHKT =>
          simpleCaseClassWithSetHKT.productArity == 8
        }
        check(property)

      }
    }
    "called with a simple case class type with deep nested higher kinded Set fields of basic types" should {
      "create a generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseClassDataGen[SimpleCaseClassWithDeepNestedSetHKT]) { simpleCaseClassWithDeepNestedSetHKT =>
          simpleCaseClassWithDeepNestedSetHKT.productArity == 8
        }
        check(property)

      }
    }
    "called with a simple case class type with higher kinded Map fields of basic types" should {
      "create a generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseClassDataGen[SimpleCaseClassWithMapHKT]) { simpleCaseClassWithMapHKT =>
          simpleCaseClassWithMapHKT.productArity == 8
        }
        check(property)

      }
    }
    "called with a simple case class type with higher kinded Map keys of basic types and deeper higher kinded value types" should {
      "create a generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseClassDataGen[SimpleCaseClassWithMapHigherKindedValueTypesHKT]) { simpleCaseClassWithMapHigherKindedValueTypesHKT =>
          simpleCaseClassWithMapHigherKindedValueTypesHKT.productArity == 8
        }
        check(property)

      }
    }
  }
  "CaseClassDataGen for nested case classes" when {
    "called with a nested simple case class type" should {
      "create a nested case class generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseClassDataGen[NestedCaseClass]) { nestedCaseClass =>
          nestedCaseClass.productArity == 1 &&
            nestedCaseClass.simple.productArity == 8
        }
        check(property)
      }
    }

    "called with a nested simple case class type in Option HKT" should {
      "create a nested case class generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseClassDataGen[NestedCaseClassWithOptionHKT]) { nestedCaseClassWithOptionHKT =>
          nestedCaseClassWithOptionHKT.productArity == 1 &&
            ((nestedCaseClassWithOptionHKT.simpleOption.isDefined &&
              nestedCaseClassWithOptionHKT.simpleOption.map(_.productArity) == Some(8)) ||
              nestedCaseClassWithOptionHKT.simpleOption.isEmpty)
        }
        check(property)
      }
    }
    "called with a nested simple case class type in deep nested Option HKT" should {
      "create a nested case class generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseClassDataGen[NestedCaseClassWithDeepNestedOptionHKT]) { nestedCaseClassWithDeepNestedOptionHKT =>
          nestedCaseClassWithDeepNestedOptionHKT.productArity == 1
        }
        check(property)
      }
    }

    "called with a nested simple case class type in List HKT" should {
      "create a nested case class generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseClassDataGen[NestedCaseClassWithListHKT]) { nestedCaseClassWithListHKT =>
          nestedCaseClassWithListHKT.productArity == 1 &&
            ((!nestedCaseClassWithListHKT.simpleList.isEmpty &&
              nestedCaseClassWithListHKT.simpleList.headOption.map(_.productArity) == Some(8)) ||
              nestedCaseClassWithListHKT.simpleList.isEmpty)
        }
        check(property)
      }
    }
    "called with a nested simple case class type in Seq HKT" should {
      "create a nested case class generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseClassDataGen[NestedCaseClassWithSeqHKT]) { nestedCaseClassWithSeqHKT =>

          nestedCaseClassWithSeqHKT.productArity == 1 &&
            ((!nestedCaseClassWithSeqHKT.simpleSeq.isEmpty &&
              nestedCaseClassWithSeqHKT.simpleSeq.headOption.map(_.productArity) == Some(8)) ||
              nestedCaseClassWithSeqHKT.simpleSeq.isEmpty)
        }
        check(property)
      }
    }
    "called with a nested simple case class type in deep nested List HKT" should {
      "create a nested case class generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseClassDataGen[NestedCaseClassWithDeepNestedListHKT]) { nestedCaseClassWithDeepNestedListHKT =>
          nestedCaseClassWithDeepNestedListHKT.productArity == 1
        }
        check(property)
      }
    }
    "called with a nested simple case class type in deep nested Seq HKT" should {
      "create a nested case class generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseClassDataGen[NestedCaseClassWithDeepNestedSeqHKT]) { nestedCaseClassWithDeepNestedSeqHKT =>
          nestedCaseClassWithDeepNestedSeqHKT.productArity == 1
        }
        check(property)
      }
    }
    "called with a nested simple case class type in Set HKT" should {
      "create a generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseClassDataGen[NestedCaseClassWithSetHKT]) { nestedCaseClassWithSetHKT =>
          nestedCaseClassWithSetHKT.productArity == 1 &&
            ((!nestedCaseClassWithSetHKT.simpleSet.isEmpty &&
              nestedCaseClassWithSetHKT.simpleSet.headOption.map(_.productArity) == Some(8)) ||
              nestedCaseClassWithSetHKT.simpleSet.isEmpty)
        }
        check(property)
      }
    }
    "called with a nested simple case class type in deep nested Set HKT" should {
      "create a generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseClassDataGen[NestedCaseClassWithDeepNestedSetHKT]) { nestedCaseClassWithDeepNestedSetHKT =>
          nestedCaseClassWithDeepNestedSetHKT.productArity == 1
        }
        check(property)
      }
    }
  }
  "CaseClassDataGen for simple classes" when {

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

  "called with an unsupported HKT" should {
    "throw IllegalArgumentException" in {
      assertThrows[IllegalArgumentException] {
        val property = forAll(CaseClassDataGen[SimpleCaseClassWithArrayHKT]) { _ =>
          true
        }
        check(property)
      }

    }
  }
}
