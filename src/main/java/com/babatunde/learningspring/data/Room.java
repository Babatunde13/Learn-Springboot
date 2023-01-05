package com.babatunde.learningspring.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ROOM")
public class Room {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROOM_ID")
    private long id;

    @Column(name = "NAME", nullable = false, length = 16)
    private String roomName;

    @Column(name = "ROOM_NUMBER", nullable = false, length = 2, unique = true)
    private String roomNumber;

    @Column(name = "BED_INFO", nullable = false, length = 2)
    private String bedInfo;


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getBedInfo() {
        return this.bedInfo;
    }

    public void setBedInfo(String bedInfo) {
        this.bedInfo = bedInfo;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", roomName='" + getRoomName() + "'" +
            ", roomNumber='" + getRoomNumber() + "'" +
            ", bedInfo='" + getBedInfo() + "'" +
            "}";
    }

}
