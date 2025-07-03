package org.example.springai.analyzer.apispec

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.ai.chat.messages.UserMessage
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.ai.chat.prompt.SystemPromptTemplate
import org.springframework.ai.converter.BeanOutputConverter
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Service

@Service
class ApiSpecAnalyzer(
    private val externalApiLoader: ExternalApiLoader,
    private val openAiChatModel: OpenAiChatModel,
    private val objectMapper: ObjectMapper
) {
    fun analyze(
        request: ApiAnalyzeRequest,
    ) {
        val apiSpecResponse = externalApiLoader.load(request.apiSpecUrl)

        val outputConverter = BeanOutputConverter(object : ParameterizedTypeReference<IoFormat.Output>() {})

        val systemPrompt = SystemPromptTemplate(ApiSpecAnalyzePrompt.SystemPrompt().message)
        val systemMessage = systemPrompt.createMessage(
            mapOf(
                "spec" to objectMapper.writeValueAsString(apiSpecResponse.body),
                "outputFormat" to outputConverter.format
            )
        )

        val userMessage = UserMessage.builder().text(
            objectMapper.writeValueAsString(
                IoFormat.Input(
                    httpMethod = request.httpMethod,
                    requestUrl = request.requestUrl
                )
            )
        ).build()

        val prompt = Prompt(listOf(systemMessage, userMessage))
        val response = openAiChatModel.call(prompt)

        print("Response: ${response}")
    }
}
