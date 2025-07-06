package org.example.springai.analyzer.error

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ErrorAnalyzeController(
    private val errorAnalyzer: LlmErrorAnalyzer
) {
    @PostMapping("/api/v0/analyze/error-message")
    fun analyze(
        @RequestBody request: ErrorAnalyzeRequest.FromMessage
    ) {
        errorAnalyzer.analyzeFromMessage(request)
    }
}
