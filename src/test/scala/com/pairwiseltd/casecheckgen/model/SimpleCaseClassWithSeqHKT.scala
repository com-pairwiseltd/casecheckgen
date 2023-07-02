package com.pairwiseltd.casecheckgen.model

case class SimpleCaseClassWithSeqHKT(aSequentialInt: Seq[Int],
                                     aSequentialString: Seq[String],
                                     aSequentialBoolean: Seq[Boolean],
                                     aSequentialLong: Seq[Long],
                                     aSequentialShort: Seq[Short],
                                     aSequentialByte: Seq[Byte],
                                     aSequentialFloat: Seq[Float],
                                     aSequentialDouble: Seq[Double])