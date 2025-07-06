package org.example.springai.analyzer.error

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest
class SlackClientTest {
    @Autowired
    lateinit var slackClient: SlackClient

    @Test
    fun `test sendErrorNotification`() {
        // Given
    }
}