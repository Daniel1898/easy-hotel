package com.example.demo.repositories;

import com.example.demo.model.User;
import com.example.demo.model.UserOrder;
import com.example.demo.model.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface UserOrderRepository extends CrudRepository<UserOrder, Integer> {
    List<UserOrder> findByFromDateGreaterThanEqualAndToDateLessThanEqual(Date start, Date end);
    List<UserOrder> findAllByUser(User user);
}
