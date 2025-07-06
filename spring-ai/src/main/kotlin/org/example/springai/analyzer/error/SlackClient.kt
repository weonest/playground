package org.example.springai.analyzer.error

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class SlackClient(
    @Value("\${slack.webhook.url}")
    private val url: String,
) {
    private val restClient: RestClient = RestClient.create()

    fun sendErrorNotification(output: IoFormat.Output) {
        val blocks = """
            {
              "blocks": [
                {
                  "type": "header",
                  "text": {
                    "type": "plain_text",
                    "text": "🚨예외 분석 리포트"
                  }
                },
                {
                  "type": "divider"
                },
                {
                  "type": "section",
                  "text": {
                      "type": "mrkdwn",
                      "text": "*핵심 예외:*\n`${output.rootCause}`"
                    }
                },
                {
                  "type": "section",
                  "text": {
                      "type": "mrkdwn",
                      "text": "*유저 행동:*\n${output.userPerspective}"
                    }
                },
                {
                  "type": "section",
                  "text": {
                    "type": "mrkdwn",
                    "text": "*상세 설명:*\n> ${output.reason}"
                  }
                },
                {
                  "type": "divider"
                },
                {
                  "type": "context",
                  "elements": [
                    {
                      "type": "mrkdwn",
                      "text": "해당 분석은 AI에 의해 생성되었습니다."
                    }
                  ]
                }
              ]
            }
        """.trimIndent()
        val body = jacksonObjectMapper().readValue(blocks, object : TypeReference<Map<String, Any>>() {})

        val response = restClient.post()
            .uri(url)
            .contentType(MediaType.APPLICATION_JSON)
            .body(body)
            .retrieve()
            .body(String::class.java)
        print(response)
    }
}