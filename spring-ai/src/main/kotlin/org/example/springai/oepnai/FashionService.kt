package org.example.springai.oepnai

import com.fasterxml.jackson.annotation.JsonPropertyDescription
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.springframework.ai.chat.messages.Message
import org.springframework.ai.chat.messages.UserMessage
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.ai.chat.prompt.SystemPromptTemplate
import org.springframework.ai.content.Media
import org.springframework.ai.converter.BeanOutputConverter
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Service
import org.springframework.util.MimeType
import org.springframework.util.MimeTypeUtils
import java.net.URI
import java.util.List


@Service
class FashionService(
    @Value("\${prompt.fashion.system-message}") private val systemText: String,
    @Value("\${prompt.fashion.user-message}") private val userText: String,
    private val openAiChatModel: OpenAiChatModel
) {
    fun chat() {
        val converter = BeanOutputConverter(object : ParameterizedTypeReference<ApiResponse<List<FabricResponse>>>() {})
        val format = converter.format

        val systemPromptTemplate = SystemPromptTemplate(systemText)
        val systemMessage: Message = systemPromptTemplate.createMessage(mapOf("format" to format))

        val userMessage = UserMessage.builder()
            .text(userText)
            .media(Media(MimeTypeUtils.IMAGE_PNG, URI.create("https://example.com/image.png")))
            .build()

        val prompt = Prompt(List.of(systemMessage, userMessage))

        val response = openAiChatModel.call(prompt)
        println("Response: ${response}")
    }
}

data class ApiResponse<T>(
    val data: T,
)

@JsonPropertyOrder("imageId", "title", "description")
data class FabricResponse(
    val imageId: String,
    val title: String,
    val description: String
)
