package org.example.springai.oepnai

import org.springframework.ai.chat.messages.Message
import org.springframework.ai.chat.messages.UserMessage
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.ai.chat.prompt.SystemPromptTemplate
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.stereotype.Service
import java.util.List
import java.util.Map


@Service
class AiService(
    private val openAiChatModel: OpenAiChatModel
) {
    fun chat() {
        val userText = """
            Tell me about three famous pirates from the Golden Age of Piracy and why they did.
            Write at least a sentence for each pirate.
            """.trimIndent()

        val userMessage: Message = UserMessage(userText)

        val systemText = """
          You are a helpful AI assistant that helps people find information.
          Your name is {name}
          You should reply to the user's request with your name and also in the style of a {voice}.
          """.trimIndent()

        val systemPromptTemplate = SystemPromptTemplate(systemText)
        val systemMessage: Message =
            systemPromptTemplate.createMessage(Map.of<String, Any>("name", "", "voice", "20th century American pirate"))

        val prompt = Prompt(List.of(userMessage, systemMessage))

        val response = openAiChatModel.call(prompt)
        print("Response: ${response}")
    }
}
