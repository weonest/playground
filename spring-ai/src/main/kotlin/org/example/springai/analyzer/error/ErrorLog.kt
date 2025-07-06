package org.example.springai.analyzer.error

class ErrorLog(
    private val exception: Exception,
    private val stackTraceAnalyzer: StackTraceAnalyzer
) {
    val stackTrace: String
    val methodSignatures: List<MethodSignatureInfo>

    init {
        val stackTraceElements = exception.stackTrace.toList()
        this.stackTrace = getStackTraceAsString(exception.message ?: "", stackTraceElements)
        this.methodSignatures = stackTraceAnalyzer.analyze(stackTraceElements)
    }

    private fun getStackTraceAsString(message: String, stackTraceElements: List<StackTraceElement>): String {
        return buildString {
            appendLine(message)
            stackTraceElements.forEach { appendLine(it) }
        }
    }
}
