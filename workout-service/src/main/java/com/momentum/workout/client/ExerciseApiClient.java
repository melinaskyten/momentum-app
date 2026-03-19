package com.momentum.workout.client;

import com.momentum.workout.dto.ExternalExerciseDTO;
import com.momentum.workout.dto.ExternalExerciseResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Arrays;
import java.util.List;

@Component
public class ExerciseApiClient {

    private final WebClient webClient;

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
            System.err.println("API returned error: " + e.getResponseBodyAsString());
            return List.of();
        } catch (Exception e) {
            System.err.println("API call failed: " + e.getMessage());
            return List.of();
        }
    }

//    public List<ExternalExerciseDTO> getExercises() {
//        try {
//            ExternalExerciseDTO[] exercises = webClient.get()
//                    .uri("/exercises")
//                    .retrieve()
//                    .bodyToMono(ExternalExerciseDTO[].class)
//                    .block();
//            return Arrays.asList(exercises);
//        } catch (WebClientResponseException e) {
//            System.err.println("API returned error: " + e.getResponseBodyAsString());
//            return List.of();
//        } catch (Exception e) {
//            System.err.println("API call failed: " + e.getMessage());
//            return List.of();
//        }
//    }

}
