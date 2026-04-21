package com.momentum.stats.controller;

import com.momentum.stats.dto.StatsResponse;
import com.momentum.stats.service.StatsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class StatsController {

    private final StatsService statsService;

    @GetMapping
    public StatsResponse getStats (HttpServletRequest request) {
        String token = (String) request.getAttribute("token");
        return statsService.getStats(token);
    }
}
