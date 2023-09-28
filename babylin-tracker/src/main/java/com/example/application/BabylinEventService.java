package com.example.application;

import org.springframework.stereotype.Service;

@Service
public class BabylinEventService {
    public static BabylinEvent createEventByType(EventType eventType) {
        return switch (eventType) {
            case MEAL -> new MealEvent();
            case SLEEP -> new SleepEvent();
            case WAKEUP -> new WakeUpEvent();
        };
    }
}
