package com.example.appspring21.repository;

import com.example.appspring21.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkerRepository extends JpaRepository<Worker,Integer> {
    Optional<Worker> findByPhoneNumber(String phoneNumber);
}
