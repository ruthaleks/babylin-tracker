package com.example.application;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class MealEvent extends BabylinEvent {
    private Double amount;
    @Override
    public String toString() {
        return "WakeUpEvent(" +
               "id=" + getId() +
               ", eventTime=" + getEventTime() +
               ", amount=" + this.amount +
               ')';
    }
}
