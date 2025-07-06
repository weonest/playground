package org.example.springai.analyzer.error


sealed class ErrorAnalyzeRequest {
    data class FromMessage(
        val httpMethod: String,
        val path: String,
        val message: String,
    ): ErrorAnalyzeRequest()

    data class FromException(
        val httpMethod: String,
        val path: String,
        val exception: Exception
    ): ErrorAnalyzeRequest()

}
