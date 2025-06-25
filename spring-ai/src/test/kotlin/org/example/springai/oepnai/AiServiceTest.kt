package org.example.springai.oepnai

import org.junit.jupiter.api.Assertions.*
import org.springframework.ai.converter.BeanOutputConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest
class AiServiceTest {

    @Autowired
    lateinit var aiService: AiService

    @Test
    fun `챗 테스트`() {
        // Given
        // When
        aiService.chat()

        // Then
        // Assertions can be added here based on the expected behavior of the chat method
    }

    @Test
    fun `포맷 출력 테스트`() {
        val converter = BeanOutputConverter(AiResponse::class.java)

        println(converter.format)
    }

}