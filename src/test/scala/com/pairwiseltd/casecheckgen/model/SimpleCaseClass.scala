package com.pairwiseltd.casecheckgen.model

import java.sql.{Date, Timestamp}
import java.time.{LocalDate, LocalDateTime, OffsetDateTime, ZonedDateTime}

case class SimpleCaseClass(anInt: Int, aString: String, aBoolean: Boolean, aLong: Long, aShort: Short, aByte: Byte,
                           aFloat: Float, aDouble: Double, aDate: Date, aTimestamp: Timestamp, aBigDecimal: BigDecimal,
                           aLocalDate: LocalDate, aLocalDateTime: LocalDateTime,
                           aZonedDateTime: ZonedDateTime, aOffSetDateTime: OffsetDateTime)
