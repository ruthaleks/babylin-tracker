package com.example.application;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class WakeUpEvent extends BabylinEvent{
    @Override
    public String toString() {
        return "WakeUpEvent(" +
               "id=" + getId() +
               ", eventTime=" + getEventTime() +
               ')';
    }
}
