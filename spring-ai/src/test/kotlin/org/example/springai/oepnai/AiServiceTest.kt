package org.example.springai.oepnai

import org.springframework.ai.converter.BeanOutputConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.ParameterizedTypeReference
import java.util.List
import java.util.concurrent.Executors
import kotlin.test.Test

@SpringBootTest
class AiServiceTest {

    @Autowired
    lateinit var aiService: AiService

    @Autowired
    lateinit var fashionService: FashionService

    @Test
    fun `챗 테스트`() {
//        aiService.chat()
        fashionService.chat()
    }

    @Test
    fun `챗 비동기 테스트`() {
        Executors.newVirtualThreadPerTaskExecutor().use { executor ->
            val futures = (1..10).map { i ->
                executor.submit {
                    println("실행 $i")
                    fashionService.chat()
                }
            }
        }
    }

    @Test
    fun `포맷 출력 테스트`() {
        val converter = BeanOutputConverter(AiResponse::class.java)
        val converter2 = BeanOutputConverter(object : ParameterizedTypeReference<List<ApiResponse<FabricResponse>>>() {})
        println(converter2.format)
    }

}