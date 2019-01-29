package com.example.demo.repositories;

import com.example.demo.model.Service;
import com.example.demo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<Service, Integer> {
    Service findByName(String name);
}