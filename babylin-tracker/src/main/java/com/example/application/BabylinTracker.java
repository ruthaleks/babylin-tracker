package com.example.application;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@ToString
public class BabylinTracker {
    @Id
    @GeneratedValue
    private Long id;
    private EventType eventType;
    private LocalDateTime time;

    public BabylinTracker(EventType type, LocalDateTime time) {
        this.eventType = type;
        this.time = time;
    }
}

