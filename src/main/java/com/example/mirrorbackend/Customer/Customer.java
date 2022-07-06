package com.example.mirrorbackend.Customer;

import com.example.mirrorbackend.ServiceRequest.ServiceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "customer") @AllArgsConstructor @NoArgsConstructor @Data
public class Customer {
    @Id
    private String customerId = UUID.randomUUID().toString().toUpperCase();

    @OneToMany( fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId")
    @JsonIgnore
    private Set<ServiceRequest> serviceRequests;

    @NotNull @Size(min = 2, max = 15, message = "Name length must be between 2 and 25")
    private String username;
     @Email @Column(unique = true) @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String email;
    @NotNull(message = "Password length must be between 6 and 30") @Size(min = 6, max=30) @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotNull @Column(unique = true) @Pattern(regexp = "05[^12A-Za-z!@#$%^&*_-]\\d{7}", message = "Phone number must be 10 digits long and starts with 05 followed by any number except 2 or 1")
    private String phoneNumber;
}