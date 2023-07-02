package com.pairwiseltd.casecheckgen.model

case class SimpleCaseClassWithDeepNestedSeqHKT(aDeepSequentialInt: Seq[Seq[Int]],
                                               aDeepSequentialString: Seq[Seq[String]],
                                               aDeepSequentialBoolean: Seq[Seq[Boolean]],
                                               aDeepSequentialLong: Seq[Seq[Long]],
                                               aDeepSequentialShort: Seq[Seq[Short]],
                                               aDeepSequentialByte: Seq[Seq[Byte]],
                                               aDeepSequentialFloat: Seq[Seq[Float]],
                                               aDeepSequentialDouble: Seq[Seq[Double]])
