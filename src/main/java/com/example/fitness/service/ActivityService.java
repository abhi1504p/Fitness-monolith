package com.example.fitness.service;

import com.example.fitness.dto.Activity.ActivityResponse;
import com.example.fitness.dto.Activity.ActivityRequest;
import com.example.fitness.model.Activity;
import com.example.fitness.model.User;
import com.example.fitness.repository.ActivityRepository;
import com.example.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;


    public ActivityResponse UserActivity(ActivityRequest activityRequest) {

        String userId = activityRequest.getUserId();
        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Activity activity = Activity.builder()
                .user(foundUser)
                .type(activityRequest.getType())
                .additionalMetrics(activityRequest.getAdditionalMetrics())
                .caloriesBurned(activityRequest.getCaloriesBurned())
                .startTime(activityRequest.getStartTime())
                .duration(activityRequest.getDuration())
                .build();
        Activity savedActivity = activityRepository.save(activity);

        return mapToActivity(savedActivity);
    }

    private ActivityResponse mapToActivity(Activity savedActivity) {
        ActivityResponse activityResponse = new ActivityResponse();

        activityResponse.setUserID(savedActivity.getUser().getId());
        activityResponse.setCaloriesBurned(savedActivity.getCaloriesBurned());
        activityResponse.setAdditionalMetrics(savedActivity.getAdditionalMetrics());
        activityResponse.setDuration(savedActivity.getDuration());
        activityResponse.setId(savedActivity.getId());
        activityResponse.setCreatedAt(savedActivity.getCreatedAt());
        activityResponse.setUpdatedAt(savedActivity.getUpdatedAt());
        activityResponse.setType(savedActivity.getType());
        activityResponse.setStartTime(savedActivity.getStartTime());
        return activityResponse;


    }

    public List<ActivityResponse> getAllActivity(String userID) {
        List<Activity> allActivityList = activityRepository.findByUserId(userID);
        return allActivityList.stream().map(this::mapToActivity).collect(Collectors.toList());
    }

}
