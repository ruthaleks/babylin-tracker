package com.example.application;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BabylinTrackerRepo extends JpaRepository<BabylinEvent, Long>{
}
