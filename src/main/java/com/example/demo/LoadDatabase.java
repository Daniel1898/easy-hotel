package com.example.demo;

import com.example.demo.model.*;
import com.example.demo.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(RoomTypeRepository repository, UserRepository userRepository,
                                   RoomRepository roomRepository, UserOrderRepository userOrderRepository, ServiceRepository sr) throws ParseException {
        Set<Role> adminSet = new HashSet<>();
        adminSet.add(Role.ADMIN);
        Set<Role> userSet = new HashSet<>();
        adminSet.add(Role.USER);
        RoomType roomType = new RoomType("test2",
                "test2",
                "/img/twin.jpeg",3000);
        RoomType roomType2 = new RoomType("test1",
                "test1",
                "/img/single.jpeg",2800);
        Room room = new Room(roomType);
        Room room2 = new Room(roomType2);
        Room room3 = new Room(roomType2);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = format.parse("2018-07-3");
        Date d2 = format.parse("2018-07-6");
        Date d3 = format.parse("2018-12-7");
        Date d4 = format.parse("2018-12-9");
        Service s1 = new Service("Breakfast",2000);
        Set<Service> services = new HashSet<>();
        services.add(s1);
        return args -> {
            repository.save(roomType2);
            User user = new User("user1","Обычный","Пользователь","e@m.r","786493","1",userSet);
            repository.save(roomType);

            roomRepository.save(room);
            roomRepository.save(room2);
            roomRepository.save(room3);


            userRepository.save(new User("admin","Даниил","Николаев","","1111","1234",adminSet));
            userRepository.save(new User("anonym","","","","","dG3sDf2eRv45",userSet));

            userRepository.save(user);

            sr.save(s1);
            userOrderRepository.save(new UserOrder(room,user,user.getName(),user.getSurname(),user.getEmail(),user.getPhoneNumber(),d1,d2,1,services,9000));
            userOrderRepository.save(new UserOrder(room,user,user.getName(),user.getSurname(),user.getEmail(),user.getPhoneNumber(),d3,d4,0,new HashSet<>(),5600));
        };
    }
}
