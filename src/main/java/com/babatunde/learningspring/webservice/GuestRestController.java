package com.babatunde.learningspring.webservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.babatunde.learningspring.business.ReservationService;
import com.babatunde.learningspring.data.Guest;
import com.babatunde.learningspring.data.Reservation;

@RestController
@RequestMapping("/api/guests")
public class GuestRestController {
    private final ReservationService reservationService;

    public GuestRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Response<Guest> createGuest(
        @RequestBody Guest guest
    ) {
        Guest createdGuest = null;
        try {
            createdGuest = this.reservationService.createGuest(guest);
        } catch (Exception e) {
            return new Response<Guest>(e, e.getMessage(), false);
        }
        Response<Guest> response = new Response<Guest>(createdGuest, "Guest created", true);
        return response;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Response<List<Guest>> getGuests() {
        return new Response<List<Guest>>(this.reservationService.getGuests(), "Guests fetched", true);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Response<Guest> getGuest(@PathVariable("id") long id) {
        Guest guest = this.reservationService.getGuest(id);
        Response<Guest> response = new Response<Guest>(guest, "Guest fetched", true);
        return response;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/reservations")
    public Response<Map<String, Object>> getGuestReservations(@PathVariable("id") long id) {
        Guest guest = this.reservationService.getGuest(id);
        List<Reservation> reservations = this.reservationService.getReservationsForGuest(id);
        Map<String, Object> response = new HashMap<>();
        response.put("guest", guest);
        response.put("reservations", reservations);

        return new Response<Map<String, Object>>(
            response, "Reservations fetched for guest: " + guest.getFirstName(), true
        );
    }
}
