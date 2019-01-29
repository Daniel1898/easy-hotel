package com.example.demo.controllers;

import com.example.demo.UserManager;
import com.example.demo.model.*;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class UserOrderController {
    @Autowired
    RoomTypeRepository roomTypeRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    UserOrderRepository userOrderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ServiceRepository serviceRepository;

    @GetMapping("/orderpage")
    public String getOrderPage(@RequestParam String from, @RequestParam String to, @RequestParam(name = "type_id", defaultValue = "1") String type, Map<String, Object> model) throws ParseException {
        model.put("auth", UserManager.isAuth());

        int type_id_num = Integer.parseInt(type);
        RoomType roomType = roomTypeRepository.findById(type_id_num);

        model.put("auth", UserManager.isAuth());
        Iterable<Service> services = serviceRepository.findAll();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        long time = (format.parse(to).getTime() - format.parse(from).getTime()) / 86400000; //count of booked days
        model.put("days", time);
        model.put("price", roomType.getPrice() * time);
        model.put("typename", roomType.getName());
        model.put("from", from);
        model.put("to", to);
        model.put("service", services);
        return "orderPage";
    }

    @PostMapping("/orderpage")
    public String postOrder(@RequestParam(name = "name") String name,
                            @RequestParam(name = "surname") String surname,
                            @RequestParam(name = "room_type") String roomType,
                            @RequestParam(name = "email") String email,
                            @RequestParam(name = "number") String number,
                            @RequestParam(name = "from") String from,
                            @RequestParam(name = "to") String to,
                            @RequestParam(name = "price") int price,
                            @RequestParam(value = "myParam[]") String[] myParams,
                            Map<String, Object> model) throws ParseException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user;
        if (!auth.getPrincipal().equals("anonymousUser")) {
            user = (User) auth.getPrincipal();
        } else {
            user = userRepository.findByUserLogin("anonym");
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = format.parse(from);
        Date d2 = format.parse(to);

        RoomType roomType1 = roomTypeRepository.findByName(roomType);
        List<Room> rooms = new ArrayList<>();
        List<Room> rooms1 = roomRepository.findByType(roomType1);
        userOrderRepository
                .findByFromDateGreaterThanEqualAndToDateLessThanEqual(d1, d2)
                .stream()
                .forEach((e) -> rooms.add(e.getRoom()));
        for (Room r : rooms) {
            rooms1.remove(r);
        }

        Set<Service> services = new HashSet<>();
        for (String s : myParams) {
            services.add(serviceRepository.findByName(name));
        }
        price += rooms1.get(0).getType().getPrice();
        UserOrder order = new UserOrder(rooms1.get(0), user, name, surname, email, number, d1, d2, 0, new HashSet<>(), price);


        userOrderRepository.save(order);
        model.put("order", name);
        model.put("num", order.getId());
        model.put("", !user.getUserLogin().equals("anonym"));
        return "successorderpage";
    }

    @GetMapping("/order/cancellation")
    public String getCancellationOrderPage() {
        return "ordercancellation";
    }

    @PostMapping("/order/cancellation")
    public String cancelOrder(@RequestParam(name = "onumber") int num) {
        UserOrder order = userOrderRepository.findById(num).get();
        order.setStatus(2);
        userOrderRepository.save(order);
        return "ordercancellation";
    }
}
