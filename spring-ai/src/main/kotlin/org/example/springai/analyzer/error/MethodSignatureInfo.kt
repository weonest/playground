package org.example.springai.analyzer.error

import com.fasterxml.jackson.annotation.JsonPropertyDescription

data class MethodSignatureInfo(
    @get:JsonPropertyDescription("클래스 이름")
    val className: String,
    @get:JsonPropertyDescription("라인 넘버")
    val lineNumber: Int,
    @get:JsonPropertyDescription("파라미터 목록")
    val parameters: List<ParameterInfo>,
    @get:JsonPropertyDescription("리턴 자료형")
    val returnType: String
) {
    data class ParameterInfo(
        @get:JsonPropertyDescription("파라미터 이름")
        val name: String,
        @get:JsonPropertyDescription("파라미터 자료형")
        val type: String
    )
}
