package com.pairwiseltd.casecheckgen.model

import scala.collection.immutable.ArraySeq


case class SimpleCaseClassWithArrayHKT(anArrayInt: ArraySeq[Int],
                                       anArrayString: ArraySeq[String],
                                       anArrayBoolean: ArraySeq[Boolean],
                                       anArrayLong: ArraySeq[Long],
                                       anArrayShort: ArraySeq[Short],
                                       anArrayByte: ArraySeq[Byte],
                                       anArrayFloat: ArraySeq[Float],
                                       anArrayDouble: ArraySeq[Double])
