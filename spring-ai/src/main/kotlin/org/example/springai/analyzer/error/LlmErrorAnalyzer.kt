package org.example.springai.analyzer.error

import org.springframework.ai.converter.BeanOutputConverter
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.stereotype.Component

@Component
class LlmErrorAnalyzer(
    private val promptFactory: PromptFactory,
    private val openAiChatModel: OpenAiChatModel,
    private val stackTraceAnalyzer: StackTraceAnalyzer,
    private val slackClient: SlackClient,
) {
    fun analyzeFromMessage(
        request: ErrorAnalyzeRequest.FromMessage,
    ): IoFormat.Output {
        val input = IoFormat.Input(
            httpMethod = request.httpMethod,
            requestUrl = request.path,
            cause = request.message,
        )

        val output = analyzeWithInput(input) ?: throw RuntimeException("응답 생성 실패")
        slackClient.sendErrorNotification(output)
        return output
    }

    fun analyzeFromException(
        request: ErrorAnalyzeRequest.FromException,
    ): IoFormat.Output {
        val errorLog = ErrorLog(request.exception, stackTraceAnalyzer)

        val input = IoFormat.Input(
            httpMethod = request.httpMethod,
            requestUrl = request.path,
            errorMessage = request.exception.message ?: "",
            methodSignatures = errorLog.methodSignatures,
            cause = errorLog.stackTrace
        )

        val output = analyzeWithInput(input) ?: throw RuntimeException("응답 생성 실패")
        slackClient.sendErrorNotification(output)
        return output
    }

    private fun analyzeWithInput(input: IoFormat.Input): IoFormat.Output? {
        val outputConverter = BeanOutputConverter(IoFormat.Output::class.java)
        val prompt = promptFactory.create(input, outputConverter)

        val response = openAiChatModel.call(prompt)
        val resultText = response.result?.output?.text
            ?: throw RuntimeException("응답 생성 실패")

        return outputConverter.convert(resultText)
    }
}
