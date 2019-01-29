package com.example.demo.repositories;

import com.example.demo.model.Room;
import com.example.demo.model.RoomType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface RoomRepository extends CrudRepository<Room, Integer> {
    List<Room> findByType(RoomType type);
}