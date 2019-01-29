package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Room {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    RoomType type;

    public Room()
    {

    }
    public Room(RoomType type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoomType getType() {
        return type;
    }

    public String getName() {
        return type.getName();
    }

    public String getDescription() {
        return type.getDescription();
    }

    public String getImgPath() {
        return type.getImgPath();
    }


    public void setType(RoomType type) {
        this.type = type;
    }
}
