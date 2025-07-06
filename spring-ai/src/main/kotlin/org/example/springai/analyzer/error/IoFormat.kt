package org.example.springai.analyzer.error

import com.fasterxml.jackson.annotation.JsonPropertyDescription
import com.fasterxml.jackson.annotation.JsonPropertyOrder

sealed class IoFormat {
    data class Input(
        @get:JsonPropertyDescription("HTTP 메소드 (예: GET, POST 등)")
        val httpMethod: String,
        @get:JsonPropertyDescription("요청 URL (예: /api/v1/resource)")
        val requestUrl: String,
        @get:JsonPropertyDescription("에러가 발생한 핵심 메세지")
        val errorMessage: String? = null,
        @get:JsonPropertyDescription("메서드 시그니쳐 목록")
        val methodSignatures: List<MethodSignatureInfo>? = null,
        @get:JsonPropertyDescription("Error Stack Trace")
        val cause: String,
    ): IoFormat()

    @JsonPropertyOrder("rootCause", "userPerspective", "reason")
    data class Output(
        @get:JsonPropertyDescription("핵심 예외 (예: NotFoundException, IllegalArgumentException 등)")
        val rootCause: String,
        @get:JsonPropertyDescription("사용자가 '/apis/1.0/image-cards/{id}' 에 GET 요청을 보냈을 때, 존재하지 않는 이미지 카드를 요청함으로써 에러가 발생했습니다.")
        val userPerspective: String,
        @get:JsonPropertyDescription("ImageCardNotFound 예외는 요청한 이미지 카드가 데이터베이스에서 찾을 수 없을 때 발생합니다. 이는 'findByIdOrThrow' 메서드가 데이터베이스 조회 후 null 반환 시 예외를 던지기 때문입니다.")
        val reason: String,
    ): IoFormat()
}
