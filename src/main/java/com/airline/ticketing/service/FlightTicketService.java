package com.airline.ticketing.service;

import com.airline.ticketing.entity.FlightEntity;
import com.airline.ticketing.entity.passenger.FareClassEntity;
import com.airline.ticketing.entity.passenger.PassengerSeatMapEntity;
import com.airline.ticketing.entity.passenger.SeatTypeEntity;
import com.airline.ticketing.entity.repo.*;
import com.airline.ticketing.entity.ticket.FlightTicketEntity;
import com.airline.ticketing.entity.ticket.PassengerDetailEntity;
import com.airline.ticketing.entity.ticket.TicketPaymentEntity;
import com.airline.ticketing.model.passenger.PassengerSeatMap;
import com.airline.ticketing.model.ticket.FlightTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
public class FlightTicketService {

    private final FlightTicketRepo ticketRepo;
    private final FlightRepo flightRepo;
    private final FareClassRepo fareClassRepo;
    private final SeatTypeRepo seatTypeRepo;
    private final PassengerSeatMapRepo seatMapRepo;

    @Autowired
    public FlightTicketService(
            @Qualifier("flight-ticket-r") FlightTicketRepo ticketRepo,
            @Qualifier("flight-r") FlightRepo flightRepo,
            @Qualifier("fare-class-r") FareClassRepo fareClassRepo,
            @Qualifier("seat-type-r") SeatTypeRepo seatTypeRepo,
            @Qualifier("passenger-seat-map-r") PassengerSeatMapRepo seatMapRepo
    ) {
        this.ticketRepo = ticketRepo;
        this.flightRepo = flightRepo;
        this.fareClassRepo = fareClassRepo;
        this.seatTypeRepo = seatTypeRepo;
        this.seatMapRepo = seatMapRepo;
    }

    /**
     * Sample card validating, not real world validation
     */
    private static boolean isValidCard(String cardNumber, String cv) {

        if (cardNumber == null || cardNumber.isEmpty()) return false;
        if (cv == null || cv.isEmpty()) return false;

        cardNumber = cardNumber.trim();
        cv = cv.trim();

        if (cardNumber.length() != 16) return false;
        if (cv.length() != 3) return false;

        for (char c : cardNumber.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        for (char c : cv.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }

        return true;
    }

    private static String maskCardNumber(String cardNumber) {

        return ""
                + cardNumber.substring(0, 4)
                + "-"
                + cardNumber.substring(4, 8)
                + "-"
                + "****"
                + "-"
                + cardNumber.substring(12, 16);
    }

    public FlightTicket buyFlight(String flightCode, char fareClassCode, String seatTypeCode, String cardNumber, String cardValidateCode, String passengerFullName) throws Exception {

        if (!FlightTicketService.isValidCard(cardNumber, cardValidateCode)) {
            throw new Exception("Not valid card data.");
        }

        FlightEntity flightEntity = this.flightRepo.findByCode(flightCode);
        if (flightEntity == null) {
            throw new Exception("Flight not found with given code: " + flightCode);
        }

        FareClassEntity fareClassEntity = this.fareClassRepo.findByCode(fareClassCode);
        if (fareClassEntity == null) {
            throw new Exception("Fare Class not found with given code: " + fareClassCode);
        }

        SeatTypeEntity seatTypeEntity = this.seatTypeRepo.findByCode(seatTypeCode);
        if (seatTypeEntity == null) {
            throw new Exception("Not valid seat type with code: " + seatTypeCode);
        }

        // specified payment amount by fare class
        double paymentAmount = flightEntity.rawTicketPrice() * fareClassEntity.getPriceCoefficient();

        // check if suitable seat is exist
        PassengerSeatMap seatMap = flightEntity.airplane().globalForm().passengerSeatMap();
        int fullnessCoefficient = ((int) seatMap.fullnessPercentage()) / 10;

        String seatSpotCode = seatMap.reserveASeat(fareClassCode, seatTypeCode);

        // update seat map
        this.seatMapRepo.save(new PassengerSeatMapEntity(seatMap));

        // calculate new amount with fullness coefficient
        if (fullnessCoefficient > 0) paymentAmount += paymentAmount * (0.1 * fullnessCoefficient);

        // represent payment
        TicketPaymentEntity ticketPaymentEntity = new TicketPaymentEntity(
                ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault()),
                maskCardNumber(cardNumber),
                paymentAmount
        );

        PassengerDetailEntity passengerDetail = new PassengerDetailEntity(passengerFullName);

        // save flight ticket
        return this.ticketRepo.save(
                new FlightTicketEntity(flightEntity, ticketPaymentEntity, passengerDetail, seatSpotCode)
        ).globalForm();
    }

    public FlightTicket findByTicketCode(String ticketCode) {

        FlightTicketEntity entity = this.ticketRepo.findByTicketCode(UUID.fromString(ticketCode));
        if(entity == null) return null;
        return this.ticketRepo.findByTicketCode(UUID.fromString(ticketCode)).globalForm();
    }
}
