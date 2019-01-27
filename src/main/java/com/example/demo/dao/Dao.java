package com.example.demo.dao;

import com.example.demo.model.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Dao {

    @Autowired
    JdbcTemplate template;

    public List<RoomType> getTypesOfFreeRooms(String from, String to)
    {
        System.out.println(" select rt.id, rt.description, rt.img_path, rt.name, rt.price from room_type as rt join room as r on rt.id=r.type_id " +
                "where from_date > CAST(" + from +
                " as DATE) " +
                "AND to_date < CAST(" + to +
                " as DATE) " +
                "group by r.type_id;");
        List list =  template.query("select rt.id, rt.description, rt.img_path, rt.name, rt.price from room_type as rt join room as r on rt.id=r.type_id " +
                        "where r.id not in " +
                        "(select r_id from user_order " +
                        "where from_date > " +
                        "CAST(\'" + from +"\' as DATE) and to_date < CAST(\'"+ to +"\' as DATE)) " +
                        "group by rt.id;",
                (resultSet, i) -> new RoomType(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("img_path"),
                        resultSet.getInt("price")));

        return list;
    }
}
