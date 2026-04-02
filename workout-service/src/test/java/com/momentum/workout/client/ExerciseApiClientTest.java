package com.momentum.workout.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.momentum.workout.dto.ExternalExerciseDTO;
import com.momentum.workout.dto.ExternalExerciseResponse;
import com.momentum.workout.dto.ExternalExerciseSingleResponse;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseApiClientTest {

    static MockWebServer mockWebServer;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private ExerciseApiClient exerciseApiClient;

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @BeforeEach
    void init() {
        String baseUrl = mockWebServer.url("/api/v1").toString();

        exerciseApiClient = new ExerciseApiClient(baseUrl);
    }

    @Test
    void getExercises() throws Exception{
        ExternalExerciseDTO mockExercise = new ExternalExerciseDTO();
        mockExercise.setExerciseId("ztAa1RK");
        mockExercise.setName("band alternating v-up");

        ExternalExerciseResponse response = new ExternalExerciseResponse();
        response.setData(List.of(mockExercise));

        mockWebServer.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(response))
                .addHeader("Content-Type", "application/json"));

        List<ExternalExerciseDTO> result = exerciseApiClient.getExercises();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("ztAa1RK", result.get(0).getExerciseId());
        assertEquals("band alternating v-up", result.get(0).getName());

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/api/v1/exercises", request.getPath());
    }

    @Test
    void getExercisesBySearch() throws Exception{
        ExternalExerciseDTO mockExercise = new ExternalExerciseDTO();
        mockExercise.setExerciseId("ztAa1RK");
        mockExercise.setName("band alternating v-up");

        ExternalExerciseResponse response = new ExternalExerciseResponse();
        response.setData(List.of(mockExercise));

        mockWebServer.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(response))
                .addHeader("Content-Type", "application/json"));

        List<ExternalExerciseDTO> result = exerciseApiClient.getExercisesBySearch("band");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("ztAa1RK", result.get(0).getExerciseId());
        assertEquals("band alternating v-up", result.get(0).getName());

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/api/v1/exercises?search=band", request.getPath());
    }

    @Test
    void getExerciseById() throws Exception {
        ExternalExerciseDTO mockExercise = new ExternalExerciseDTO();
        mockExercise.setExerciseId("ztAa1RK");
        mockExercise.setName("band alternating v-up");

        ExternalExerciseSingleResponse response = new ExternalExerciseSingleResponse();
        response.setData(mockExercise);

        mockWebServer.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(response))
                .addHeader("Content-Type", "application/json"));

        ExternalExerciseDTO result = exerciseApiClient.getExerciseById("ztAa1RK");

        assertNotNull(result);
        assertEquals("ztAa1RK", result.getExerciseId());
        assertEquals("band alternating v-up", result.getName());

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/api/v1/exercises/ztAa1RK", request.getPath());
    }
}