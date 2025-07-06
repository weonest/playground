package org.example.springai.analyzer.apispec

import com.fasterxml.jackson.annotation.JsonPropertyDescription

sealed class IoFormat {
    data class Input(
        @get:JsonPropertyDescription("HTTP 메소드 (예: GET, POST 등)")
        val httpMethod: String,
        @get:JsonPropertyDescription("요청 URL (예: /api/v1/resource)")
        val requestUrl: String,
    ): IoFormat()

    data class Output(
        @get:JsonPropertyDescription("입력 받은 httpMethod 그대로")
        val httpMethod: String,
        @get:JsonPropertyDescription("입력 받은 URL 그대로")
        val requestUrl: String,
        @get:JsonPropertyDescription("찾은 API 요청의 summary 부분 그대로, 없으면 공백으로 제공함")
        val summary: String,
    ): IoFormat()
}
