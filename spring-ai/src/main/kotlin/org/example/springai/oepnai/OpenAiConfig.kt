package org.example.springai.oepnai

import org.springframework.ai.model.ApiKey
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.ai.openai.OpenAiChatOptions
import org.springframework.ai.openai.api.OpenAiApi
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenAiConfig(
    @Value("\${spring.ai.openai.api-key}") private val apiKey: String
) {
    @Bean
    fun openAiChatModel(): OpenAiChatModel {
        val key = ApiKey { apiKey }
        val openAiApi = OpenAiApi.builder().apiKey(key).build()
        val openAiOptions = OpenAiChatOptions.builder().model(OpenAiApi.ChatModel.GPT_4_O.value).build()
        return OpenAiChatModel.builder()
            .openAiApi(openAiApi)
            .defaultOptions(openAiOptions)
            .build()
    }
}
