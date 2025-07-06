package org.example.springai.analyzer.error

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValues
import org.example.springai.analyzer.apispec.IoFormat
import org.springframework.ai.converter.BeanOutputConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.ParameterizedTypeReference
import kotlin.test.Test

@SpringBootTest
class LlmErrorAnalyzerTest {
    @Autowired
    lateinit var llmErrorAnalyzer: LlmErrorAnalyzer

    @Test
    fun `예외 메세지 분석 테스트`() {
        val request = ErrorAnalyzeRequest.FromMessage(
            httpMethod = "GET",
            path = "/api/v1/image-cards/12345",
            message = "\"im.toss.teens.imagecard.adapter.persistence.ImageCardRepository\$ImageCardNotFound: 존재하지 않는 이미지카드에요.\n" +
                    "at im.toss.teens.imagecard.adapter.persistence.ImageCardRepository\$ImageCardNotFound.<clinit>(ImageCardRepository.kt)\n" +
                    "at im.toss.teens.imagecard.adapter.persistence.ImageCardRepository.findByIdOrThrow\$lambda\$0(ImageCardRepository.kt:27)\n" +
                    "at im.toss.teens.imagecard.adapter.persistence.ImageCardRepository.findByIdOrThrow(ImageCardRepository.kt:27)\n" +
                    "at im.toss.teens.home.adapter.outbound.persistence.config.RoutingDataSourceInterceptor.annotationRoutingDataSourceAnnotation(RoutingDataSourceInterceptor.kt:19)\n" +
                    "at im.toss.teens.imagecard.adapter.persistence.ImageCardRepository\$\$SpringCGLIB\$\$0.findByIdOrThrow(<generated>)\n" +
                    "at im.toss.teens.imagecard.application.services.GetImageCardService.execute(GetImageCardService.kt:34)\n" +
                    "at im.toss.teens.api.controller.imagecard.web.ImageCardController.getImageCard(ImageCardController.kt:47)\n" +
                    "at im.toss.teens.api.controller.imagecard.web.ImageCardController\$\$SpringCGLIB\$\$0.getImageCard(<generated>)\n" +
                    "\""
        )

        val output = llmErrorAnalyzer.analyzeFromMessage(request)
        println(output)
    }

    @Test
    fun `예외 클래스 분석 테스트`() {
        try
        {
            // 예외를 강제로 발생시킴
            val list = listOf<String>()
            println(list[4]) // IndexOutOfBoundsException 발생
        } catch (e: Exception) {
            val request = ErrorAnalyzeRequest.FromException(
                httpMethod = "GET",
                path = "/api/v1/image-cards",
                exception = IndexOutOfBoundsException("toIndex (4) is greater than size (0).")
            )
            val output = llmErrorAnalyzer.analyzeFromException(request)
            println(output)
        }
    }

    @Test
    fun `포맷 출력 테스트`() {
        val inputConverter = BeanOutputConverter(object : ParameterizedTypeReference<IoFormat.Input>() {})
        println(inputConverter.format)
    }
}
