package com.pairwiseltd.casecheckgen.model

case class SimpleCaseClassWithSetHKT(aSetOfInt: Set[Int],
                                     aSetOfString: Set[String],
                                     aSetOfBoolean: Set[Boolean],
                                     aSetOfLong: Set[Long],
                                     aSetOfShort: Set[Short],
                                     aSetOfByte: Set[Byte],
                                     aSetOfFloat: Set[Float],
                                     aSetOfDouble: Set[Double])
