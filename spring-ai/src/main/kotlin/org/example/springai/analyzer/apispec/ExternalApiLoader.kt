package org.example.springai.analyzer.apispec

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class ExternalApiLoader(
    private val restClient: RestClient = RestClient.create()
) {
    fun load(uri: String): ResponseEntity<Map<*, *>> {
        return restClient.get()
            .uri(uri)
            .retrieve()
            .toEntity(Map::class.java)
    }
}
