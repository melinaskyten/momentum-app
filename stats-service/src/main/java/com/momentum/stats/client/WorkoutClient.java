package com.momentum.stats.client;

import com.momentum.stats.dto.WorkoutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WorkoutClient {

    private final RestClient restClient;

    public List<WorkoutDTO> getWorkoutsByUserId (String token) {
        return restClient.get()
                .uri("/workout")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(new ParameterizedTypeReference<List<WorkoutDTO>>() {});
    }
}
