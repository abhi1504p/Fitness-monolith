package com.example.fitness.controller;

import com.example.fitness.dto.Activity.ActivityResponse;
import com.example.fitness.dto.Activity.ActivityRequest;
import com.example.fitness.model.Activity;
import com.example.fitness.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService activityService;

    //    post the activity of the user
    @PostMapping("/activities")
    public ResponseEntity<ActivityResponse> saveUserActivity(@RequestBody ActivityRequest activityRequest) {
        try {
            return ResponseEntity.ok(activityService.UserActivity(activityRequest));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // get all the activity done by the user
    @GetMapping("/activities")
    public ResponseEntity<List<ActivityResponse>> getUserActivity(@RequestHeader(value = "X-User-ID") String userId) {
        try {
            return ResponseEntity.ok(activityService.getAllActivity(userId));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
