package com.example.Movies_online.repositories;

import com.example.Movies_online.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface CustomerRepository extends JpaRepository<Customer, Long> {
    }
