package com.pairwiseltd.casecheckgen.model

case class SimpleCaseClassWithNestedOptionHKT(aNestedOptionalInt: Option[Option[Int]],
                                              aNestedOptionalString: Option[Option[String]],
                                              aNestedOptionalBoolean: Option[Option[Boolean]],
                                              aNestedOptionalBigDecimal: Option[Option[BigDecimal]],
                                              aNestedOptionalShort: Option[Option[Short]],
                                              aNestedOptionalByte: Option[Option[Byte]],
                                              aNestedOptionalFloat: Option[Option[Float]],
                                              aNestedOptionalDouble: Option[Option[Double]])
