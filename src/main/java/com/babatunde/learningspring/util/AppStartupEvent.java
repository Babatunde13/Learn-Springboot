package com.babatunde.learningspring.util;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.babatunde.learningspring.data.Guest;
import com.babatunde.learningspring.data.GuestRepository;
import com.babatunde.learningspring.data.Reservation;
import com.babatunde.learningspring.data.ReservationRepository;
import com.babatunde.learningspring.data.Room;
import com.babatunde.learningspring.data.RoomRepository;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {

    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    public AppStartupEvent(
        RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository
    ) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("Application started");
        Iterable<Room> rooms = this.roomRepository.findAll();
        rooms.forEach(System.out::println);
        System.out.println("Number of rooms: " + this.roomRepository.count());
        System.out.println();

        Iterable<Guest> guests = this.guestRepository.findAll();
        guests.forEach(System.out::println);
        System.out.println("Number of guests: " + this.guestRepository.count());
        System.out.println();

        Iterable<Reservation> reservations = this.reservationRepository.findAll();
        reservations.forEach(System.out::println);
        System.out.println("Number of reservations: " + this.reservationRepository.count());
    }
}
