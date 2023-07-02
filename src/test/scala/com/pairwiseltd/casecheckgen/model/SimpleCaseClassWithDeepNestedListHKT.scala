package com.pairwiseltd.casecheckgen.model

case class SimpleCaseClassWithDeepNestedListHKT(aDeepSequentialInt: List[List[Int]],
                                                aDeepSequentialString: List[List[String]],
                                                aDeepSequentialBoolean: List[List[Boolean]],
                                                aDeepSequentialLong: List[List[Long]],
                                                aDeepSequentialShort: List[List[Short]],
                                                aDeepSequentialByte: List[List[Byte]],
                                                aDeepSequentialFloat: List[List[Float]],
                                                aDeepSequentialDouble: List[List[Double]])
