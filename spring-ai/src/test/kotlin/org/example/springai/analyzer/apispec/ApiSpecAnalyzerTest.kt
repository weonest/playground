package org.example.springai.analyzer.apispec

import org.example.springai.oepnai.AiResponse
import org.example.springai.oepnai.AiService
import org.example.springai.oepnai.ApiResponse
import org.example.springai.oepnai.FabricResponse
import org.example.springai.oepnai.FashionService
import org.junit.jupiter.api.Assertions.*
import org.springframework.ai.converter.BeanOutputConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.ParameterizedTypeReference
import java.util.List
import java.util.concurrent.Executors
import kotlin.test.Test

@SpringBootTest
class ApiSpecAnalyzerTest {
    @Autowired
    lateinit var aiService: AiService

    @Autowired
    lateinit var fashionService: FashionService

    @Autowired
    lateinit var apiSpecAnalyzer: ApiSpecAnalyzer

    @Test
    fun `API 스펙 분석 테스트`() {
        apiSpecAnalyzer.analyze(
            ApiAnalyzeRequest(
                apiSpecUrl = "https://petstore.swagger.io/v2/swagger.json",
                httpMethod = "POST",
                requestUrl = "/pet/{petId}/uploadImage",
                cause = """
                    com.mangkyu.PetRepository$\PetNotFoundException: cannot find pet
                    	at com.mangkyu.PetRepository.findById(PetRepository.java:10)
                    	at com.mangkyu.PetRepository${'$'}$\SpringCGLIB${'$'}${'$'}0.findById(<generated>)
                    	at com.mangkyu.PetService.uploadImage(PetService.java:17)
                    	at com.mangkyu.PetController.uploadImage(PetController.java:19)
                """
            )
        )
    }

    @Test
    fun `포맷 출력 테스트`() {
        val inputConverter = BeanOutputConverter(object : ParameterizedTypeReference<IoFormat.Input>() {})
        println(inputConverter.format)
    }
}