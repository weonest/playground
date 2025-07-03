package org.example.springai.analyzer.apispec

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiAnalyzeController(
    private val apiSpecAnalyzer: ApiSpecAnalyzer
) {
    @GetMapping("/api/v0/analyze")
    fun analyze(
        @RequestBody request: ApiAnalyzeRequest
    ): String {
        return apiSpecAnalyzer.analyze(request)
    }
}