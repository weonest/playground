package org.example.springai.analyzer

import org.example.springai.analyzer.external.ExternalApiLoader
import org.example.springai.analyzer.prompt.ApiSpecAnalyzePrompt
import org.example.springai.analyzer.prompt.IoFormat
import org.springframework.ai.chat.messages.UserMessage
import org.springframework.ai.chat.prompt.SystemPromptTemplate
import org.springframework.ai.converter.BeanOutputConverter
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.core.ParameterizedTypeReference

class AnalyzeService(
    private val externalApiLoader: ExternalApiLoader,
    private val openAiChatModel: OpenAiChatModel,
) {
    fun analyzeApiSpec(
        apiSpecUrl: String
    ): String {
        val response = externalApiLoader.load(apiSpecUrl)

        val inputConverter = BeanOutputConverter(
            object : ParameterizedTypeReference<IoFormat.Input>() {}
        )

        val outputConverter = BeanOutputConverter(
            object : ParameterizedTypeReference<IoFormat.Output>() {}
        )

        val systemPrompt = SystemPromptTemplate(ApiSpecAnalyzePrompt.SystemPrompt().message)

        val systemMessage = systemPrompt.createMessage(
            mapOf(
                "spec" to response.body,
                "inputFormat" to inputConverter.format,
                "outputFormat" to outputConverter.format
            )
        )

        val userMessage = UserMessage.builder()
            .text("")
            .build()
    }
}