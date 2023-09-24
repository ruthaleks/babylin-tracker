package com.example.application;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class BabylinTracker {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime sleepRecord;

    public BabylinTracker(LocalDateTime sleepRecord) {
        this.sleepRecord = sleepRecord;
    }
}
