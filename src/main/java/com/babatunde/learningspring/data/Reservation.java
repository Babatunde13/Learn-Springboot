package com.babatunde.learningspring.data;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "RESERVATION")
public class Reservation {
    @Id
    @GeneratedValue(strategy  = GenerationType.AUTO)
    @Column(name = "RESERVATION_ID")
    private long id;

    @Column(name = "ROOM_ID", nullable = false)
    private long roomId;

    @Column(name = "GUEST_ID", nullable = false)
    private long guestId;

    @Column(name = "RES_DATE")
    private Date reservationDate;


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRoomId() {
        return this.roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getGuestId() {
        return this.guestId;
    }

    public void setGuestId(long guestId) {
        this.guestId = guestId;
    }

    public Date getReservationDate() {
        return this.reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", roomId='" + getRoomId() + "'" +
            ", guestId='" + getGuestId() + "'" +
            ", resDate='" + getReservationDate() + "'" +
            "}";
    }

}
