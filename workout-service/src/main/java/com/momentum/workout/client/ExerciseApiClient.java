package com.momentum.workout.client;

import com.momentum.workout.Exception.ExternalClientException;
import com.momentum.workout.Exception.ExternalServerException;
import com.momentum.workout.dto.ExternalExerciseDTO;
import com.momentum.workout.dto.ExternalExerciseResponse;
import com.momentum.workout.dto.ExternalExerciseSingleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.net.URI;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;

@Component
public class ExerciseApiClient {

    private final WebClient webClient;
    private static final Logger logger = LoggerFactory.getLogger(ExerciseApiClient.class);

    public ExerciseApiClient(@Value("${exercise.api.base-url}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    private WebClient.ResponseSpec prepareRequest(Function<UriBuilder, URI> uriFunction) {
        return webClient.get()
                .uri(uriFunction)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        clientResponse.bodyToMono(String.class).map(body -> {
                            logger.warn("4xx error: {}", body);
                            return new ExternalClientException("Client Error: " + body);
                        })
                )
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                        clientResponse.bodyToMono(String.class).map(body -> {
                            logger.warn("5xx error: {}", body);
                            return new ExternalServerException("Server Error: " + body);
                        })
                );
    }

    public List<ExternalExerciseDTO> getExercises() {
        try {
            ExternalExerciseResponse response = prepareRequest(uriBuilder ->
                    uriBuilder
                            .path("/exercises")
                            .build())
                    .bodyToMono(ExternalExerciseResponse.class)
                    .timeout(Duration.ofSeconds(3))
                    .retryWhen(
                            Retry.backoff(3, Duration.ofMillis(500))
                                    .filter(throwable -> throwable instanceof ExternalServerException)
                    )
                    .onErrorResume(e -> {
                        logger.error("Unexpected error when fetching exercise: {}", e.getMessage(), e);
                        return Mono.empty();
                    })
                    .block();

            return response != null ? response.getData() : List.of();
        }catch (Exception e) {
            logger.error("Unexpected error when fetching exercises: {}", e.getMessage());
            return List.of();
        }
    }

    public List<ExternalExerciseDTO> getExercisesBySearch(String query) {
        try {
            ExternalExerciseResponse response = prepareRequest(uriBuilder ->
                    uriBuilder
                            .path("/exercises")
                            .queryParam("search", query)
                            .build())
                    .bodyToMono(ExternalExerciseResponse.class)
                    .timeout(Duration.ofSeconds(3))
                    .retryWhen(
                            Retry.backoff(3, Duration.ofMillis(500))
                                    .filter(throwable -> throwable instanceof ExternalServerException)
                    )
                    .onErrorResume(e -> {
                        logger.error("API call failed when fetching exercises by search {}: {}", query, e.getMessage(), e);
                        return Mono.empty();
                    })
                    .block();

            return response != null ? response.getData() : List.of();
        }catch (Exception e) {
            logger.error("Unexpected error when fetching exercises: {}", e.getMessage(), e);
            return List.of();
        }
    }

    public ExternalExerciseDTO getExerciseById(String id) {
        try {
            ExternalExerciseSingleResponse response = prepareRequest(uriBuilder ->
                    uriBuilder
                            .path("/exercises/{id}")
                            .build(id))
                    .bodyToMono(ExternalExerciseSingleResponse.class)
                    .timeout(Duration.ofSeconds(3))
                    .retryWhen(
                            Retry.backoff(3, Duration.ofMillis(500))
                                    .filter(throwable -> throwable instanceof ExternalServerException)
                    )
                    .onErrorResume(e -> {
                        logger.error("API call failed when fetching exercise by id {}: {}", id, e.getMessage(), e);
                        return Mono.empty();
                    })
                    .block();

            return response != null ? response.getData() : null;
        }catch (Exception e) {
            logger.error("Unexpected error when fetching exercise: {}", e.getMessage(), e);
            return null;
        }
    }
}