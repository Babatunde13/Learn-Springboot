package com.babatunde.learningspring.webservice;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.babatunde.learningspring.business.ReservationService;
import com.babatunde.learningspring.business.RoomReservation;
import com.babatunde.learningspring.data.Reservation;
import com.babatunde.learningspring.util.DateUtils;

@RestController
@RequestMapping("/api/reservations/")
public class RoomReservationRestController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    public RoomReservationRestController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Response<List<RoomReservation>> getReservations(
        @RequestParam(value = "date", required = false) String dateString
    ) {
        Date date = this.dateUtils.createDateFromString(dateString);
        List<RoomReservation> roomReservations = this.reservationService.getRoomReservationsForDate(date);
        return new Response<List<RoomReservation>>(
            roomReservations, "Room reservations fetched successfully", true
        );
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Response<Reservation> getReservation(
        @PathVariable("id") long id
    ) {
        Reservation reservation = this.reservationService.getReservation(id);
        return new Response<Reservation>(
            reservation, "Reservation fetched successfully", true
        );
    }
}
