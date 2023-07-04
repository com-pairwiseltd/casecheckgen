package com.pairwiseltd.casecheckgen.model

import java.time.{OffsetDateTime, ZonedDateTime}

case class SimpleCaseClassWithDeepNestedSeqHKT(aDeepSequentialInt: Seq[Seq[Int]],
                                               aDeepSequentialString: Seq[Seq[String]],
                                               aDeepSequentialBoolean: Seq[Seq[Boolean]],
                                               aDeepSequentialLong: Seq[Seq[Long]],
                                               aDeepSequentialShort: Seq[Seq[Short]],
                                               aDeepSequentialByte: Seq[Seq[OffsetDateTime]],
                                               aDeepSequentialFloat: Seq[Seq[ZonedDateTime]],
                                               aDeepSequentialDouble: Seq[Seq[Double]])
