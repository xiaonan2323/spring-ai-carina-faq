package com.example.carina.config;

import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.vectorstore.PgVectorStore;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@EnableRetry
public class CarinaConfig {

    @Bean
    public VectorStore vectorStore(EmbeddingClient embeddingClient, JdbcTemplate jdbcTemplate) {
        return new PgVectorStore(jdbcTemplate, embeddingClient);
    }

    @Bean
    public SearchRequest searchRequest() {
        SearchRequest searchRequest = SearchRequest.defaults();
        searchRequest.withTopK(4);
        searchRequest.withSimilarityThreshold(0.75);
        return searchRequest;
    }


}
