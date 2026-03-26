package com.momentum.workout.client;

import com.momentum.workout.dto.ExternalExerciseDTO;
import com.momentum.workout.dto.ExternalExerciseResponse;
import com.momentum.workout.dto.ExternalExerciseSingleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Component
public class ExerciseApiClient {

    private final WebClient webClient;
    private static final Logger logger = LoggerFactory.getLogger(ExerciseApiClient.class);

    public ExerciseApiClient() {
        this.webClient = WebClient.builder()
                .baseUrl("https://exercisedb.dev/api/v1")
                .build();
    }

    public List<ExternalExerciseDTO> getExercises() {
        try {
            ExternalExerciseResponse response = webClient.get()
                    .uri("/exercises")
                    .retrieve()
                    .bodyToMono(ExternalExerciseResponse.class)
                    .block();

            return response != null ? response.getData() : List.of();

        } catch (WebClientResponseException e) {
            logger.error("External API returned error. Status code: {} Body: {}",
                    e.getStatusCode(),
                    e.getResponseBodyAsString());
            return List.of();
        } catch (Exception e) {
            logger.error("Unexpected error when calling external API: {}", e.getMessage());
            return List.of();
        }
    }

    public List <ExternalExerciseDTO> getExercisesBySearch(String query) {
        try {
            ExternalExerciseResponse response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/exercises")
                            .queryParam("search", query)
                            .build())
                    .retrieve()
                    .bodyToMono(ExternalExerciseResponse.class)
                    .block();

            return response != null ? response.getData() : List.of();

        } catch (WebClientResponseException e) {
            logger.error("External API returned error. Status code: {} Body: {}",
                    e.getStatusCode(),
                    e.getResponseBodyAsString());
            return List.of();
        } catch (Exception e) {
            logger.error("Unexpected error when calling external API: {}", e.getMessage());
            return List.of();
        }
    }

    public ExternalExerciseDTO getExerciseById(String id) {
        try{
            ExternalExerciseSingleResponse response = webClient.get()
                    .uri("/exercises/{id}", id)
                    .retrieve()
                    .bodyToMono(ExternalExerciseSingleResponse.class)
                    .block();

            return response != null ? response.getData() : null;
        } catch (WebClientResponseException e) {
            logger.error("External API returned error. Status code: {} Body: {}",
                    e.getStatusCode(),
                    e.getResponseBodyAsString());
            return null;
        } catch (Exception e) {
            logger.error("Unexpected error when fetching exercise: {}", e.getMessage());
            return null;
        }
    }
}