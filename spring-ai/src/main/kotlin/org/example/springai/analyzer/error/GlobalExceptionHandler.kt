package org.example.springai.analyzer.error

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Exception

@RestControllerAdvice
class GlobalExceptionHandler(
    private val slackClient: SlackClient,
    private val errorAnalyzer: LlmErrorAnalyzer,
) {

    @ExceptionHandler
    private fun handleException(request: HttpServletRequest, exception: Exception) {
        errorAnalyzer.analyzeFromException(
            request = ErrorAnalyzeRequest.FromException(
                httpMethod = request.method,
                path = request.requestURI.toString(),
                exception = exception,
            )
        )
        // 로깅 로직..
        slackClient.sendErrorNotification()
    }
}