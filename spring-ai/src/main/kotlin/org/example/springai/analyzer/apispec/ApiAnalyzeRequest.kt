package org.example.springai.analyzer.apispec

data class ApiAnalyzeRequest(
    val apiSpecUrl: String,
    val httpMethod: String,
    val requestUrl: String,
    val cause: String,
)
