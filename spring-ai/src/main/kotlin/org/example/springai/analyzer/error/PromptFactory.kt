package org.example.springai.analyzer.error

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.ai.chat.messages.UserMessage
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.ai.chat.prompt.SystemPromptTemplate
import org.springframework.ai.converter.BeanOutputConverter
import org.springframework.stereotype.Component

@Component
class PromptFactory(private val objectMapper: ObjectMapper) {
    fun create(input: IoFormat.Input, outputConverter: BeanOutputConverter<IoFormat.Output>): Prompt {
        val systemPrompt = SystemPromptTemplate(ErrorAnalyzePrompt.SystemPrompt().message)
        val systemMessage = systemPrompt.createMessage(
            mapOf(
                "outputFormat" to outputConverter.format
            )
        )

        val userMessage = UserMessage.builder().text(
            objectMapper.writeValueAsString(input)
        ).build()

        return Prompt(listOf(systemMessage, userMessage))
    }
}
