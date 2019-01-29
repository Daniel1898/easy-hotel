package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class UserOrder {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "r_id")
    private Room room;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "u_id")
    private User user;

    private int status;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "services",
            joinColumns = { @JoinColumn(name = "service_id") },
            inverseJoinColumns = { @JoinColumn(name = "order_id") })
    private Set<Service> services = new HashSet<>();


    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    @Temporal(TemporalType.DATE)
    private Date fromDate;
    @Temporal(TemporalType.DATE)
    private Date toDate;
    private int price;

    public UserOrder() {
    }

    public UserOrder(Room room, User user, String name,
                     String surname, String email, String phoneNumber,
                     Date fromDate, Date toDate, int status, Set<Service> services,int price) {
        this.room = room;
        this.user = user;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.status = status;
        this.services = services;
        this.price = price;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String  getStatus() {
        switch (status)
        {
            case 0: return "Ожидание";
            case 1: return "Принят";
            case 2: return "Отменен";
            default: return "Неизвестен";
        }
    }
    public boolean isAccepted()
    {
        if (status == 0)
        {
            return false;
        } else
        {
            return true;
        }
    }
    public int getStatusNumber() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public int getRoomNumber()
    {
        return room.getId();
    }
}
