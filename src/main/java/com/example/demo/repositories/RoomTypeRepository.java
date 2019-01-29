package com.example.demo.repositories;


import com.example.demo.model.RoomType;
import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface RoomTypeRepository extends CrudRepository<RoomType, Integer> {
    RoomType findById(int id);
    RoomType findByName(String name);
}
