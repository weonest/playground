package org.example.springai.oepnai

import com.fasterxml.jackson.annotation.JsonPropertyDescription
import org.springframework.ai.chat.messages.Message
import org.springframework.ai.chat.messages.UserMessage
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.ai.chat.prompt.SystemPromptTemplate
import org.springframework.ai.converter.BeanOutputConverter
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.stereotype.Service
import java.util.List


@Service
class AiService(
    private val openAiChatModel: OpenAiChatModel
) {
    fun chat() {
        val converter = BeanOutputConverter(AiResponse::class.java)
        val format = converter.format
        val systemText = """
         당신은 정치학을 가르치는 전문 교수입니다.
            앞으로의 모든 질문에 대해서 최대한의 정보를 동원하여 답변해 주세요.
            답변을 할 때는 반드시 정치적 중립을 유지해야 합니다.
            
            답변은 아래 지시에 맞게 JSON을 출력하세요.
            {format}
          """.trimIndent()
        val systemPromptTemplate = SystemPromptTemplate(systemText)
        val systemMessage: Message =
            systemPromptTemplate.createMessage(mapOf("format" to format))

        val userText = """
            앞으로 이란과 이스라엘은 전쟁을 하게 되는지 궁금합니다.
            판단의 근거와 예상되는 결과를 알려주세요.
            근거는 최대 5개 작성해 주세요.
            근거는 110글자 이상 200글자 이하로 작성해 주세요.
            """.trimIndent()
        val userMessage: Message = UserMessage(userText)

        val prompt = Prompt(List.of(systemMessage, userMessage))

        val response = openAiChatModel.call(prompt)
        println("Response: ${response}")
    }
}

data class AiResponse(
    @get:JsonPropertyDescription("답변 결과")
    var result: String,
    @get:JsonPropertyDescription("판단의 근거에는 인덱스를 담아줘. ex) 1. 근거 내용")
    var reason: List<String>,
)
