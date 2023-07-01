package com.pairwiseltd.casecheckgen.utils

import scala.reflect.runtime.universe._

object TypeTagUtils {
  private val mirror = runtimeMirror(getClass.getClassLoader)

  implicit class SymbolWithTypeTagSupport(symbol: Symbol) {
    def asTypeTag : TypeTag[_] = {
      val symbolType = symbol.typeSignature
      val typeTag = TypeTag.apply(mirror, new reflect.api.TypeCreator {
        def apply[U <: reflect.api.Universe with Singleton](m: reflect.api.Mirror[U]): U#Type =
          if (symbolType.typeSymbol.isClass && symbolType.typeSymbol.asClass.isCaseClass) {
            symbolType.asInstanceOf[U#Type]
          } else {
            throw new IllegalArgumentException("Symbol does not represent a case class.")
          }
      })
      typeTag
    }
  }
}
