/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.symbols

import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

object StandardClassIds {

    private val BASE_KOTLIN_PACKAGE = FqName("kotlin")
    private val BASE_REFLECT_PACKAGE = BASE_KOTLIN_PACKAGE.child(Name.identifier("reflect"))
    private fun String.baseId() = ClassId(BASE_KOTLIN_PACKAGE, Name.identifier(this))
    private fun ClassId.unsignedId() = ClassId(BASE_KOTLIN_PACKAGE, Name.identifier("U" + shortClassName.identifier))
    private fun String.reflectId() = ClassId(BASE_REFLECT_PACKAGE, Name.identifier(this))
    private fun Name.primitiveArrayId() = ClassId(Array.packageFqName, Name.identifier(identifier + Array.shortClassName.identifier))

    val Nothing = "Nothing".baseId()
    val Unit = "Unit".baseId()
    val Any = "Any".baseId()
    val Enum = "Enum".baseId()
    val Annotation = "Annotation".baseId()
    val Array = "Array".baseId()

    val Boolean = "Boolean".baseId()
    val Char = "Char".baseId()
    val Byte = "Byte".baseId()
    val Short = "Short".baseId()
    val Int = "Int".baseId()
    val Long = "Long".baseId()
    val Float = "Float".baseId()
    val Double = "Double".baseId()

    val UByte = Byte.unsignedId()
    val UShort = Short.unsignedId()
    val UInt = Int.unsignedId()
    val ULong = Long.unsignedId()

    val String = "String".baseId()

    val KProperty = "KProperty".reflectId()

    val Comparable = "Comparable".baseId()
    val Number = "Number".baseId()

    val Function = "Function".baseId()

    fun byName(name: String) = name.baseId()
    fun reflectByName(name: String) = name.reflectId()

    val primitiveArrayTypeByElementType: Map<ClassId, ClassId> = mutableMapOf<ClassId, ClassId>().apply {
        fun addPrimitive(id: ClassId) {
            put(id, id.shortClassName.primitiveArrayId())
        }

        addPrimitive(Boolean)
        addPrimitive(Char)

        addPrimitive(Byte)
        addPrimitive(Short)
        addPrimitive(Int)
        addPrimitive(Long)

        addPrimitive(UByte)
        addPrimitive(UShort)
        addPrimitive(UInt)
        addPrimitive(ULong)

        addPrimitive(Float)
        addPrimitive(Double)
    }

    val elementTypeByPrimitiveArrayType = primitiveArrayTypeByElementType.map { (k, v) -> v to k }.toMap()
}
