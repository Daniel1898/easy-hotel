package com.example.demo.controllers;

import com.example.demo.UserManager;
import com.example.demo.model.Service;
import com.example.demo.model.User;
import com.example.demo.model.UserOrder;
import com.example.demo.repositories.ServiceRepository;
import com.example.demo.repositories.UserOrderRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminPanelContoller {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserOrderRepository userOrderRepository;
    @Autowired
    ServiceRepository serviceRepository;


    @GetMapping("/users")
    public String userList(Map<String, Object> model) {
        model.put("auth", UserManager.isAuth());
        Iterable<User> users = userRepository.findAll();
        model.put("users", users);
        return "userList";
    }

    @PostMapping("/users/delete/{user}")
    public String deleteUser(@PathVariable User user) {
        userRepository.delete(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/orders")
    public String showOrder(@RequestParam(defaultValue = " ") String from, @RequestParam(defaultValue = " ") String to, Map<String, Object> model) throws ParseException {
        model.put("auth", UserManager.isAuth());
        Iterable<UserOrder> list;
        if (!from.equals(" ") && !to.equals(" ")) {
            //if form with date is empty find all orders
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = format.parse(from);
            Date d2 = format.parse(to);
            list = userOrderRepository.findByFromDateGreaterThanEqualAndToDateLessThanEqual(d1, d2);
        } else {
            list = userOrderRepository.findAll();
        }
        model.put("orders", list);
        return "orderList";
    }

    @GetMapping("/services")
    public String getServices(Map<String, Object> model) {
        model.put("auth", UserManager.isAuth());
        Iterable<Service> services = serviceRepository.findAll();
        model.put("service", services);
        return "services";
    }

    @PostMapping("/services")
    public String addServices(@RequestParam(name = "name") String name, @RequestParam(name = "price") int price, Map<String, Object> model) {
        if (serviceRepository.findByName(name) == null) {
            serviceRepository.save(new Service(name, price));
        } else {
            model.put("warning", true);
        }
        return "redirect:/admin/services";
    }

    @GetMapping("/services/edit/{service}")
    public String getProfileEdit(@PathVariable Service service, Map<String, Object> model) {
        model.put("auth", UserManager.isAuth());
        model.put("service", service);
        return "serviceEdit";
    }

    @PostMapping("/services/edit/{service}")
    public String editService(@RequestParam String name,
                              @RequestParam int price,
                              @PathVariable Service service) {
        service.setName(name);
        service.setPrice(price);
        serviceRepository.save(service);
        return "redirect:/admin/services";
    }

    @PostMapping("/services/delete/{service}")
    public String deleteService(@PathVariable Service service) {
        serviceRepository.delete(service);
        return "redirect:/services";
    }

    @PostMapping("/orders/accept/{userOrder}")
    public String acceptOrder(@PathVariable UserOrder userOrder) {
        userOrder.setStatus(1);
        userOrderRepository.save(userOrder);
        return "redirect:/admin/orders";
    }


    @PostMapping("/orders/delete/{userOrder}")
    public String deleteOrder(@PathVariable UserOrder userOrder) {
        userOrderRepository.delete(userOrder);
        return "redirect:/admin/orders";
    }

}
