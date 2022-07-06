package com.example.mirrorbackend.ServiceRequest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, String> {
}