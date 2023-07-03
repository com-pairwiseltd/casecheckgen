package com.pairwiseltd.casecheckgen.model

case class SimpleCaseClassWithMapHKT(aMapInt: Map[Int, Int],
                                     aMapString: Map[String, String],
                                     aMapBoolean: Map[String, Boolean],
                                     aMapLong: Map[Long, Long],
                                     aMapShort: Map[Short, Short],
                                     aMapByte: Map[String, Byte],
                                     aMapFloat: Map[Float, Float],
                                     aMapDouble: Map[Double, Double])
