package com.babatunde.learningspring.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.babatunde.learningspring.business.ReservationService;
import com.babatunde.learningspring.data.Guest;
import com.babatunde.learningspring.data.Reservation;

@Controller
@RequestMapping("/guests")
public class GuestController {
    private final ReservationService reservationService;

    public GuestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getGuests(Model model) {
        model.addAttribute("guests", this.reservationService.getGuests());
        return "guests";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String getGuest(
        @PathVariable("id") long id,
        Model model
    ) {
        Guest guest = this.reservationService.getGuest(id);
        model.addAttribute("guest", guest);
        return "guest";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/reservations")
    public String getGuestReservations(
        @PathVariable("id") long id,
        Model model
    ) {
        Guest guest = this.reservationService.getGuest(id);
        List<Reservation> reservations = this.reservationService.getReservationsForGuest(id);
        model.addAttribute("guest", guest);
        model.addAttribute("reservations", reservations);
        return "guest-reservations";
    }
}
