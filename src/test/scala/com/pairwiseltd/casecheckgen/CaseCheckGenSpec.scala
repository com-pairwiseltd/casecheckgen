package com.pairwiseltd.casecheckgen

import com.pairwiseltd.casecheckgen.model._
import org.scalacheck.Gen
import org.scalacheck.Prop.forAll
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.Checkers

import scala.collection.immutable.ArraySeq

class CaseCheckGenSpec extends AnyWordSpec
  with Matchers
  with Checkers {
  "CaseClassDataGen for simple case classes" when {
    // Simple case class
    "called with a simple case class type" should {
      "create a simple case class generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseCheckGen[SimpleCaseClass]) { simple =>
          simple.productArity == 15
        }
        check(property)
      }
    }
    "called with a simple case class type with higher kinded Option fields with basic types" should {
      "create a generator to be used with scalacheck forAll quantifier" in {

        val property = forAll(CaseCheckGen[SimpleCaseClassWithOptionHKT]) { simpleCaseClassWithOptionHKT =>
          simpleCaseClassWithOptionHKT.productArity == 8
        }
        check(property)
      }
    }
    "called with a simple case class type with deeper higher kinded Option fields" should {
      "create a generator to be used with scalacheck forAll quantifier" in {

        val property = forAll(CaseCheckGen[SimpleCaseClassWithNestedOptionHKT]) { simpleCaseClassWithNestedOptionHKT =>
          simpleCaseClassWithNestedOptionHKT.productArity == 8
        }
        check(property)
      }
    }

    "called with a simple case class type with higher kinded Seq fields of basic types" should {
      "create a generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseCheckGen[SimpleCaseClassWithSeqHKT]) { simpleCaseClassWithSeqHKT =>
          simpleCaseClassWithSeqHKT.productArity == 8
        }
        check(property)

      }
    }
    "called with a simple case class type with deep nested higher kinded Seq fields of basic types" should {
      "create a generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseCheckGen[SimpleCaseClassWithDeepNestedSeqHKT]) { simpleCaseClassWithDeepNestedSeqHKT =>

          simpleCaseClassWithDeepNestedSeqHKT.productArity == 8
        }
        check(property)
      }
    }
    "called with a simple case class type with higher kinded List fields of basic types" should {
      "create a generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseCheckGen[SimpleCaseClassWithListHKT]) { simpleCaseClassWithListHKT =>
          simpleCaseClassWithListHKT.productArity == 8
        }
        check(property)

      }
    }
    "called with a simple case class type with deep nested higher kinded List fields of basic types" should {
      "create a generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseCheckGen[SimpleCaseClassWithDeepNestedListHKT]) { simpleCaseClassWithDeepNestedListHKT =>
          simpleCaseClassWithDeepNestedListHKT.productArity == 8
        }
        check(property)

      }
    }

    "called with a simple case class type with higher kinded Set fields of basic types" should {
      "create a generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseCheckGen[SimpleCaseClassWithSetHKT]) { simpleCaseClassWithSetHKT =>
          simpleCaseClassWithSetHKT.productArity == 8
        }
        check(property)

      }
    }
    "called with a simple case class type with deep nested higher kinded Set fields of basic types" should {
      "create a generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseCheckGen[SimpleCaseClassWithDeepNestedSetHKT]) { simpleCaseClassWithDeepNestedSetHKT =>
          simpleCaseClassWithDeepNestedSetHKT.productArity == 8
        }
        check(property)

      }
    }
    "called with a simple case class type with higher kinded Map fields of basic types" should {
      "create a generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseCheckGen[SimpleCaseClassWithMapHKT]) { simpleCaseClassWithMapHKT =>
          simpleCaseClassWithMapHKT.productArity == 8
        }
        check(property)

      }
    }
    "called with a simple case class type with higher kinded Map keys of basic types and deeper higher kinded value types" should {
      "create a generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseCheckGen[SimpleCaseClassWithMapHigherKindedValueTypesHKT]) { simpleCaseClassWithMapHigherKindedValueTypesHKT =>
          simpleCaseClassWithMapHigherKindedValueTypesHKT.productArity == 8
        }
        check(property)

      }
    }
  }
  "CaseClassDataGen for nested case classes" when {
    "called with a nested simple case class type" should {
      "create a nested case class generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseCheckGen[NestedCaseClass]) { nestedCaseClass =>
          nestedCaseClass.productArity == 1 &&
            nestedCaseClass.simple.productArity == 15
        }
        check(property)
      }
    }

    "called with a nested simple case class type in Option HKT" should {
      "create a nested case class generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseCheckGen[NestedCaseClassWithOptionHKT]) { nestedCaseClassWithOptionHKT =>
          nestedCaseClassWithOptionHKT.productArity == 1 &&
            ((nestedCaseClassWithOptionHKT.simpleOption.isDefined &&
              nestedCaseClassWithOptionHKT.simpleOption.map(_.productArity) == Some(15)) ||
              nestedCaseClassWithOptionHKT.simpleOption.isEmpty)
        }
        check(property)
      }
    }
    "called with a nested simple case class type in deep nested Option HKT" should {
      "create a nested case class generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseCheckGen[NestedCaseClassWithDeepNestedOptionHKT]) { nestedCaseClassWithDeepNestedOptionHKT =>
          nestedCaseClassWithDeepNestedOptionHKT.productArity == 1
        }
        check(property)
      }
    }

    "called with a nested simple case class type in List HKT" should {
      "create a nested case class generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseCheckGen[NestedCaseClassWithListHKT]) { nestedCaseClassWithListHKT =>
          nestedCaseClassWithListHKT.productArity == 1 &&
            ((!nestedCaseClassWithListHKT.simpleList.isEmpty &&
              nestedCaseClassWithListHKT.simpleList.headOption.map(_.productArity) == Some(15)) ||
              nestedCaseClassWithListHKT.simpleList.isEmpty)
        }
        check(property)
      }
    }
    "called with a nested simple case class type in Seq HKT" should {
      "create a nested case class generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseCheckGen[NestedCaseClassWithSeqHKT]) { nestedCaseClassWithSeqHKT =>

          nestedCaseClassWithSeqHKT.productArity == 1 &&
            ((!nestedCaseClassWithSeqHKT.simpleSeq.isEmpty &&
              nestedCaseClassWithSeqHKT.simpleSeq.headOption.map(_.productArity) == Some(15)) ||
              nestedCaseClassWithSeqHKT.simpleSeq.isEmpty)
        }
        check(property)
      }
    }
    "called with a nested simple case class type in deep nested List HKT" should {
      "create a nested case class generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseCheckGen[NestedCaseClassWithDeepNestedListHKT]) { nestedCaseClassWithDeepNestedListHKT =>
          nestedCaseClassWithDeepNestedListHKT.productArity == 1
        }
        check(property)
      }
    }
    "called with a nested simple case class type in deep nested Seq HKT" should {
      "create a nested case class generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseCheckGen[NestedCaseClassWithDeepNestedSeqHKT]) { nestedCaseClassWithDeepNestedSeqHKT =>
          nestedCaseClassWithDeepNestedSeqHKT.productArity == 1
        }
        check(property)
      }
    }
    "called with a nested simple case class type in Set HKT" should {
      "create a generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseCheckGen[NestedCaseClassWithSetHKT]) { nestedCaseClassWithSetHKT =>
          nestedCaseClassWithSetHKT.productArity == 1 &&
            ((!nestedCaseClassWithSetHKT.simpleSet.isEmpty &&
              nestedCaseClassWithSetHKT.simpleSet.headOption.map(_.productArity) == Some(15)) ||
              nestedCaseClassWithSetHKT.simpleSet.isEmpty)
        }
        check(property)
      }
    }
    "called with a nested simple case class type in deep nested Set HKT" should {
      "create a generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseCheckGen[NestedCaseClassWithDeepNestedSetHKT]) { nestedCaseClassWithDeepNestedSetHKT =>
          nestedCaseClassWithDeepNestedSetHKT.productArity == 1
        }
        check(property)
      }
    }
    "called with a nested simple case class type in Map HKT" should {
      "create a generator to be used with scalacheck forAll quantifier" in {
        val property = forAll(CaseCheckGen[NestedCaseClassWithMapHigherKindedValueTypesHKT]) { nestedCaseClassWithMapHigherKindedValueTypesHKT =>
          nestedCaseClassWithMapHigherKindedValueTypesHKT.productArity == 8
        }
        check(property)
      }
    }
  }
  "CaseClassDataGen for simple classes" when {

    "called with a simple class type" should {
      "throw IllegalArgumentException" in {
        assertThrows[IllegalArgumentException] {
          val property = forAll(CaseCheckGen[SimpleClass]) { _ =>
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
        val property = forAll(CaseCheckGen[SimpleCaseClassWithArrayHKT]) { _ =>
          true
        }
        check(property)
      }

    }
  }

  "called with an unsupported HKT with a proper custom handler suited for the unsupported HKT" should {
    "create a generator to be used with scalacheck forAll quantifier" in {
      import com.pairwiseltd.casecheckgen.utils.TypeTagUtils._

      import scala.reflect.runtime.universe._
      val typeTag = implicitly[TypeTag[SimpleCaseClassWithArrayHKT]]

      def customHandler: PartialFunction[Type, Gen[Any]] = {
        case t if t.asTypeTag.tpe.typeSymbol == typeOf[ArraySeq[Any]].typeSymbol =>
          Gen.listOfN(3, CaseCheckGen(tag = t.typeArgs.head.asTypeTag, customHandler = Some(customHandler))).map(x => ArraySeq(x))
      }

      val property = forAll(CaseCheckGen[SimpleCaseClassWithArrayHKT](tag = typeTag,
        customHandler = Some(customHandler))) { p =>
        true
      }
      check(property)
    }
  }
}
