package com.pairwiseltd.casecheckgen.model

case class NestedCaseClassWithMapHigherKindedValueTypesHKT(aMapInt: Map[Int, Seq[SimpleCaseClass]],
                                                           aMapString: Map[String, Set[SimpleCaseClass]],
                                                           aMapBoolean: Map[String, Option[SimpleCaseClass]],
                                                           aMapLong: Map[Long, List[SimpleCaseClass]],
                                                           aMapShort: Map[Short, Seq[Seq[SimpleCaseClass]]],
                                                           aMapByte: Map[String, Option[Option[SimpleCaseClass]]],
                                                           aMapFloat: Map[Float, Set[Seq[SimpleCaseClass]]],
                                                           aMapDouble: Map[Double, Map[String, List[SimpleCaseClass]]])
