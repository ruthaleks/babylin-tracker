package com.example.application;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class SleepEvent extends BabylinEvent{
    @Override
    public String toString() {
        return "SleepEvent(" +
               "id=" + getId() +
               ", eventTime=" + getEventTime() +
               ')';
    }
}
