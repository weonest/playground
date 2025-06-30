package org.example.springai.analyzer.prompt

data class IoFormat(
    val input: Input,
    val output: Output
) {
    data class Input(
        val httpMethod: String,
        val requestUrl: String,
        val spec: String
    )

    data class Output(
        val httpMethod: String,
        val requestUrl: String,
        val summary: String,
        val cause: String
    )
}
