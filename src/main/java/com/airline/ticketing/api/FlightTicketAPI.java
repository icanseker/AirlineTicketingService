package com.airline.ticketing.api;

import com.airline.ticketing.api.request.model.FlightTicketDetails;
import com.airline.ticketing.api.response.BasicResponse;
import com.airline.ticketing.model.ticket.FlightTicket;
import com.airline.ticketing.service.FlightTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flight-ticket")
public class FlightTicketAPI {

    private final FlightTicketService flightTicketService;

    @Autowired
    public FlightTicketAPI(FlightTicketService flightTicketService) {
        this.flightTicketService = flightTicketService;
    }

    @PostMapping("/add")
    public BasicResponse buyFlight(@RequestBody FlightTicketDetails ticketDetails) {

        BasicResponse response = new BasicResponse();

        try {

            FlightTicket ticket = this.flightTicketService.buyFlight(
                    ticketDetails.flightCode,
                    ticketDetails.fareClassCode,
                    ticketDetails.seatTypeCode,
                    ticketDetails.cardNumber,
                    ticketDetails.cardValidateCode,
                    ticketDetails.passengerFullName
            );

            response.setMessage("Flight ticket added successfully.");
            response.addProperty("Ticket code", ticket.ticketCode());
            response.addProperty("Flight code", ticket.flight().code());
            response.addProperty("Passenger name", ticket.passengerDetail().fullName());
            response.addProperty("Seat spot code", ticket.seatSpotCode());
            response.addProperty("Card number", ticket.payment().cardNumber());
            response.addProperty("Payment amount", ticket.payment().amount() + " dollars");
        } catch (Exception e) {
            response.setMessage("Flight ticket could not added.");
            response.addProperty("Error message", e.getMessage());
        }

        return response;
    }

    @GetMapping("/find/{ticket-code}")
    public BasicResponse findByTicketCode(@PathVariable("ticket-code") String ticketCode) {

        BasicResponse response = new BasicResponse();
        FlightTicket ticket = flightTicketService.findByTicketCode(ticketCode);

        if (ticket == null)
            response.setMessage("No ticket found with given code.");

        else {
            response.addProperty("Flight code", ticket.flight().code());
            response.addProperty("Passenger name", ticket.passengerDetail().fullName());
            response.addProperty("Seat spot code", ticket.seatSpotCode());
        }

        return response;
    }
}
