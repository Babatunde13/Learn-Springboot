package com.babatunde.learningspring.business;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.babatunde.learningspring.data.ReservationRepository;
import com.babatunde.learningspring.data.Room;
import com.babatunde.learningspring.data.Guest;
import com.babatunde.learningspring.data.GuestRepository;
import com.babatunde.learningspring.data.Reservation;
import com.babatunde.learningspring.data.RoomRepository;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final GuestRepository guestRepository;
    private final RoomRepository roomRepository;

    public ReservationService(
        ReservationRepository reservationRepository,
        GuestRepository guestRepository,
        RoomRepository roomRepository
    ) {
        this.reservationRepository = reservationRepository;
        this.guestRepository = guestRepository;
        this.roomRepository = roomRepository;
    }
    
    public List<RoomReservation> getRoomReservationsForDate(Date date) {
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getRoomName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getId(), roomReservation);
        });

        Iterable<Reservation> reservations = this.reservationRepository
            .findReservationByReservationDate(new java.sql.Date(date.getTime()));
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            roomReservation.setDate(date);
            Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
            roomReservation.setGuestId(guest.getId());
        });

        List<RoomReservation> roomReservations = new ArrayList<>();
        for (Long id : roomReservationMap.keySet()) {
            roomReservations.add(roomReservationMap.get(id));
        }

        roomReservations.sort((o1, o2) -> {
            if (o1.getRoomName().equals(o2.getRoomName())) {
                return o1.getRoomNumber().compareTo(o2.getRoomNumber());
            }
            return o1.getRoomName().compareTo(o2.getRoomName());
        });

        return roomReservations;
    }
    public List<Guest> getGuests() {
        Iterable<Guest> guests = this.guestRepository.findAll();
        List<Guest> guestList = new ArrayList<>();
        guests.forEach(guestList::add);
        guestList.sort((o1, o2) -> {
            if (o1.getLastName().equals(o2.getLastName())) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
            return o1.getLastName().compareTo(o2.getLastName());
        });
        return guestList;
    }

    public Guest getGuest(long guestId) {
        return this.guestRepository.findById(guestId).get();
    }

    public Reservation getReservation(long reservationId) {
        return this.reservationRepository.findById(reservationId).get();
    }

    public void saveGuest(Guest guest) {
        this.guestRepository.save(guest);
    }

    public List<Reservation> getReservationsForGuest(long guestId) {
        Iterable<Reservation> reservations = this.reservationRepository.findReservationByGuestId(guestId);
        List<Reservation> reservationList = new ArrayList<>();
        reservations.forEach(reservationList::add);
        reservationList.sort((o1, o2) -> o1.getReservationDate().compareTo(o2.getReservationDate()));
        return reservationList;
    }
}
