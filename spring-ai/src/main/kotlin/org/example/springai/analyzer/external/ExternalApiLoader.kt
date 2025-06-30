package org.example.springai.analyzer.external

import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestClient

class ExternalApiLoader(
    private val restClient: RestClient
) {
    fun load(uri: String): ResponseEntity<Map<*, *>> {
        return restClient.get()
            .uri(uri)
            .retrieve()
            .toEntity(Map::class.java)
    }
}
