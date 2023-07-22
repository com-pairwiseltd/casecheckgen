# casecheckgen
Effortless ScalaCheck Generator Creation for Case Classes

casecheckgen is an open source Scala library designed to streamline the process of generating Scalacheck generators 
for Scala case classes. Writing generators for complex case classes in Scalacheck can be cumbersome, 
often involving repetitive code. With casecheckgen, you can effortlessly generate custom generators for your case classes, 
saving time and reducing the chances of introducing bugs.

By leveraging the Scala case class type information, casecheckgen automates the generation of property-based test data, 
allowing developers to focus on writing test cases instead of boilerplate generator code. 
The library supports a wide range of data types, including nested case classes, collections, and user-defined types, 
ensuring comprehensive test coverage.

Additionally, developers can add custom handlers to support required Higher Kinded Types, 
ensuring flexibility and adaptability for diverse testing scenarios.

Whether you're building robust unit tests or exploring property-based testing, casecheckgen empowers you to accelerate 
your Scala projects' testing process while maintaining code quality and reliability. 
Join the community and start simplifying your Scalacheck generator creation with casecheckgen!

## Usage

Add the dependency to your project.

```sbt
libraryDependencies += "com.pairwiseltd" % "casecheckgen" % "x.x.x" % Test
```

Creating your first case class scala check generator is easy.

```scala
case class YourCaseClass(...)

val property = forAll(CaseCheckGen[YourCaseClass]) { yourCaseClass =>
  // assertions here.
}
check(property)
```

You can also add a custom handler for unsupported higher kinded types, collections. The below
example is for unsupported HKT ArraySeq.

```scala
case class CaseClassWithUnsupportedHKT(...)

import com.pairwiseltd.casecheckgen.utils.TypeTagUtils._

import scala.reflect.runtime.universe._
val typeTag = implicitly[TypeTag[CaseClassWithUnsupportedHKT]]

def customHandler: PartialFunction[Type, Gen[Any]] = {
  case t if t.asTypeTag.tpe.typeSymbol == typeOf[ArraySeq[Any]].typeSymbol =>
    Gen.listOfN(3, CaseCheckGen(tag = t.typeArgs.head.asTypeTag, customHandler = Some(customHandler))).map(x => ArraySeq(x))
}

val property = forAll(CaseCheckGen[SimpleCaseClassWithArrayHKT](tag = typeTag,
  customHandler = Some(customHandler))) { simpleCaseClassWithArrayHKT =>
  // assertions here.
}
check(property)
```


## Third-Party Libraries

This project incorporates the following third-party library:

- [Scalacheck](https://github.com/typelevel/scalacheck) version 1.X
- Licensed under the BSD 3-Clause License
