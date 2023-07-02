package com.pairwiseltd.casecheckgen.model

case class SimpleCaseClassWithHigherKindedType(anOptionalInt: Option[Int],
                                               anOptionalString: Option[String],
                                               anOptionalBoolean: Option[Boolean],
                                               anOptionalLong: Option[Long],
                                               anOptionalShort: Option[Short],
                                               anOptionalByte: Option[Byte],
                                               anOptionalFloat: Option[Float],
                                               anOptionalDouble: Option[Double])
