package com.pairwiseltd.casecheckgen.model

import java.time.{LocalDate, LocalDateTime}

case class SimpleCaseClassWithDeepNestedSetHKT(aDeepSetOfInt: Set[Set[Int]],
                                               aDeepSetOfString: Set[Set[String]],
                                               aDeepSetOfBoolean: Set[Set[Boolean]],
                                               aDeepSetOfLong: Set[Set[Long]],
                                               aDeepSetOfShort: Set[Set[Short]],
                                               aDeepSetOfByte: Set[Set[Byte]],
                                               aDeepSetOfFloat: Set[Set[LocalDateTime]],
                                               aDeepSetOfDouble: Set[Set[LocalDate]])
