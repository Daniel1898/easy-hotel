package com.example.demo.controllers;


import com.example.demo.UserManager;
import com.example.demo.repositories.RoomRepository;
import com.example.demo.repositories.RoomTypeRepository;
import com.example.demo.model.Room;
import com.example.demo.model.RoomType;
import com.example.demo.repositories.UserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class RoomsController {
    @Autowired
    RoomTypeRepository roomTypeRep;
    @Autowired
    RoomRepository roomRep;
    @Autowired
    UserOrderRepository userOrderRepository;

    @GetMapping(path = "/rooms")
    public String getRooms(@RequestParam String from, @RequestParam String to,
                           Map<String, Object> model) throws ParseException {
        model.put("auth", UserManager.isAuth()); //check user authorisation

        List<Room> rooms = (List<Room>) roomRep.findAll();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = format.parse(from);   //get date from request parameter
        Date d2 = format.parse(to);
        Set<Room> rooms1 = new HashSet<>();

        /*
         * find all existing orders from d1 to d2 and
         * remove all rooms booked in this period
         */
        userOrderRepository
                .findByFromDateGreaterThanEqualAndToDateLessThanEqual(d1, d2)
                .stream()
                .forEach((e) -> rooms1.add(e.getRoom()));
        for (Room r : rooms1) {
            rooms.remove(r);
        }
        Set<RoomType> roomTypes = new HashSet<>();
        for (Room r : rooms) {
            roomTypes.add(r.getType());
        }

        model.put("from", from);
        model.put("to", to);
        model.put("rooms", roomTypes);
        return "rooms";
    }

}
