package com.pairwiseltd.casecheckgen.model

case class SimpleCaseClassWithSeqHKT(aSequentialInt: Seq[Int],
                                     aSequentialString: Seq[String],
                                     aSequentialBoolean: Seq[Boolean],
                                     aSequentialLong: Seq[Long],
                                     aSequentialShort: Seq[Short],
                                     aSequentialBigDecimal: Seq[BigDecimal],
                                     aSequentialFloat: Seq[Float],
                                     aSequentialDouble: Seq[Double])