package com.pairwiseltd.casecheckgen.model

case class SimpleCaseClassWithMapHigherKindedValueTypesHKT(aMapInt: Map[Int, Seq[Int]],
                                                           aMapString: Map[String, Set[String]],
                                                           aMapBoolean: Map[String, Option[Boolean]],
                                                           aMapLong: Map[Long, List[Long]],
                                                           aMapShort: Map[Short, Seq[Seq[Short]]],
                                                           aMapBigDecimal: Map[String, Option[Option[BigDecimal]]],
                                                           aMapFloat: Map[Float, Set[Seq[Float]]],
                                                           aMapDouble: Map[Double, Map[String, List[Double]]])
