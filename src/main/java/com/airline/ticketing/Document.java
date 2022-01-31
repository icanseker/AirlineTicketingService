package com.airline.ticketing;

import com.airline.ticketing.model.*;
import com.airline.ticketing.model.airplane.AirPlaneCategory;
import com.airline.ticketing.model.airplane.AirPlaneModel;
import com.airline.ticketing.model.location.City;
import com.airline.ticketing.model.location.Country;
import com.airline.ticketing.model.location.Timezone;
import com.airline.ticketing.model.passenger.AirFareClass;
import com.airline.ticketing.model.passenger.PassengerSeat;
import com.airline.ticketing.model.passenger.PassengerSeatMap;
import com.airline.ticketing.model.passenger.PassengerSeatType;
import com.airline.ticketing.model.ticket.FlightTicket;
import com.airline.ticketing.model.ticket.PassengerDetail;
import com.airline.ticketing.model.ticket.TicketPayment;

import java.time.ZonedDateTime;

public class Document {

    public static void main(String[] args) {

        // APP DOCUMENTATION

        /*
         * City class: define cities by unique city code and city name.
         * Country class: define countries by unique alpha 3 code and state name.
         */
        City city = new City("CITY_CODE", "CITY_NAME");
        Country country = new Country("XXX", "COUNTRY_NAME");

        /*
         * Timezone class: define timezone ids represent airport location zone offset,
         * using convert departure and landing times based on ports.
         *
         * If an airplane flights from Istanbul to Tokyo, departure and landing times will be converted 'automatically' location based.
         */
        Timezone timezone = new Timezone("Asia/Tokyo");

        /*
         * PortLocation class: represents Airport map location with city, country, timezone, longitude and latitude
         */
        PortLocation portLocation_departure = new PortLocation(country, city, "Longitude", "Latitude", timezone);
        PortLocation portLocation_landing = new PortLocation(country, city, "Longitude", "Latitude", timezone);

        /*
         * Airport class: define airports with unique IATA code, an airport name and a port location.
         * So, we can filter airports by city, country or IATA code etc.
         */
        Airport airport_departure = new Airport("IATA", "AIRPORT NAME", portLocation_departure);
        Airport airport_landing = new Airport("IATA", "AIRPORT NAME", portLocation_landing);

        /*
         * AirRoute class: Of course we can define route from an airport to another airport with unique route code and proper distance.
         */
        AirRoute route1 = new AirRoute("ROUTE CODE", airport_departure, airport_landing, 1000); // distance in km
        AirRoute route2 = new AirRoute("ROUTE CODE", airport_landing, airport_departure, 1005);

        /*
         * Air Fare class: define fare classes with unique code, definition and price coefficient like:
         *      A,First Class Discounted,3.2
         *      C,Business Class,1.6
         *      E,Economy/Coach Discounted,0.8
         *      J,Business Class Premium,2
         *      F,First Class Premium,4.72
         *      R,First Class Suite or Supersonic,3.6
         *      Y,Economy/Coach,1
         *
         * When passenger choose a fare class on buying ticket, flight based default raw price will re-calculated by these coefficients.
         */
        AirFareClass fareClass = new AirFareClass('C', "DEFINITION", 1.0);

        /*
         * Passenger Seat Type: define different seat types, every seat of airplane has its specific seat type and fare class definition. Examples:
         *      STD,Standard Seat
         *      STD-BH,Standard & Bulkhead Seat
         *      AFL,Angle-Flat Seat
         *      FLB,Flat Bed Seat
         *      FLB-BH,Flat Bed & Bulkhead Seat
         *      RCL,Recliner Seat
         *      RCL-BH,Recliner & Bulkhead Seat
         *
         * When passenger select a seat type with fear class, seat map check if a seat is suitable.
         */
        PassengerSeatType seatType = new PassengerSeatType("CODE", "DEFINITION");

        /*
         * Seat class: define some seats with spot code, type and fare class
         */
        PassengerSeat seat = new PassengerSeat("1J", seatType, fareClass);

        /*
         * Passenger Seat Map: it is a map of seats, collects seats' features and seat availabilities, keep reserved seats, airplane fullness etc.
         * An example of seat map:
         */
        PassengerSeatMap default_psm = new PassengerSeatMap("DEFAULT_PSM", "Default seat map.");
        default_psm.addSeat("1A", "FLB-BH", 'C');
        default_psm.addSeat("1B", "FLB-BH", 'C');
        default_psm.addSeat("1D", "FLB-BH", 'C');
        default_psm.addSeat("1E", "FLB-BH", 'C');
        default_psm.addSeat("1J", "FLB-BH", 'C');
        default_psm.addSeat("1K", "FLB-BH", 'C');

        for (int i = 2; i <= 6; i++) {
            default_psm.addSeat(i + "A", "AFL", 'C');
            default_psm.addSeat(i + "B", "AFL", 'C');
            default_psm.addSeat(i + "D", "AFL", 'C');
            default_psm.addSeat(i + "E", "AFL", 'C');
            default_psm.addSeat(i + "J", "AFL", 'C');
            default_psm.addSeat(i + "K", "AFL", 'C');
        }

        default_psm.addSeat("7A", "STD-BH", 'E');
        default_psm.addSeat("7B", "STD-BH", 'E');
        default_psm.addSeat("7D", "STD-BH", 'E');
        default_psm.addSeat("7E", "STD-BH", 'E');
        default_psm.addSeat("7F", "STD-BH", 'E');
        default_psm.addSeat("7G", "STD-BH", 'E');
        default_psm.addSeat("7J", "STD-BH", 'E');
        default_psm.addSeat("7K", "STD-BH", 'E');

        for (int i = 8; i <= 33; i++) {
            default_psm.addSeat(i + "A", "STD", 'E');
            default_psm.addSeat(i + "B", "STD", 'E');
            default_psm.addSeat(i + "D", "STD", 'E');
            default_psm.addSeat(i + "E", "STD", 'E');
            default_psm.addSeat(i + "F", "STD", 'E');
            default_psm.addSeat(i + "G", "STD", 'E');
            default_psm.addSeat(i + "J", "STD", 'E');
            default_psm.addSeat(i + "K", "STD", 'E');
        }

        for (int i = 34; i <= 37; i++) {
            default_psm.addSeat(i + "A", "STD", 'E');
            default_psm.addSeat(i + "B", "STD", 'E');
            default_psm.addSeat(i + "D", "STD", 'E');
            default_psm.addSeat(i + "E", "STD", 'E');
            default_psm.addSeat(i + "F", "STD", 'E');
            default_psm.addSeat(i + "J", "STD", 'E');
            default_psm.addSeat(i + "K", "STD", 'E');
        }

        /*
         * Airplane Category: describes airplane type with unique code and definition. So, we know each airplane category based specifications.
         * Examples:
         *
         *      JPJ,Jumbo Passenger Jet
         *      MsPJ,Mid-size Passenger Jet
         *      LPJ,Light Passenger Jet
         *      MsBJ,Mid-Size Business Jet
         *      LBJ,Light Business Jet
         *      VLJ,Very Light Jet
         */
        AirPlaneCategory airPlaneCategory = new AirPlaneCategory("CODE", "DEFINITION");

        /*
         * Airplane Model: Each airplane has specific model,
         * this class will represent this with ICAO code, airplane category, seat map, average speed etc.
         *
         * Every airplane model must have a seat map, as default.
         * If and airplane seat map is different from its model, we can define new seat map for the airplane.
         *
         * When calculate estimated Flight time, we use route distance and airplane model average speed.
         */
        AirPlaneModel airPlaneModel = new AirPlaneModel("ICAO", "DEFINITION", airPlaneCategory, default_psm, 600);

        /*
         * Airlines Class: We can define airlines company with ist unique IATA code and its name.
         * Every airplane owned by an airlines company.
         *
         * When we produce flight code, we will use airlines IATA and route code.
         * Some examples:
         *
         *      TK,Turkish Airlines CO.
         *      PC,Pegasus Airlines
         *      AC,Air China
         */
        Airlines airlines = new Airlines("IATA", "NAME");

        /*
         * Airplane Class: An airplane defined by unique code, name and represent its model, specifications, owner, seat map etc.
         * Some examples:
         */
        Airplane one = new Airplane("THY-ANKA-333", "ANKA-333", airPlaneModel, airlines, default_psm);
        Airplane two = new Airplane("PCA-SIRIUS-A", "OTTOMAN", airPlaneModel, airlines, null);
        /*
         * null defined passenger seat map means, this airplane has its model default psm.
         */

        /*
         * Flight class: contains route, airplane, date-time, defined by unique flight code.
         */
        Flight flight = new Flight("CODE", route1, one, ZonedDateTime.now());

        /*
         * Passenger Detail class contains data about the passenger, for example its full name.
         */
        PassengerDetail passengerDetail = new PassengerDetail("NAME OF PASSENGER");

        /*
         * Ticket Payment class contains payment date-time, card number and payment amount.
         */
        TicketPayment ticketPayment = new TicketPayment(ZonedDateTime.now(), "CARD_NUMBER", 200.0);

        /*
         * Flight Ticket contains flight details, ticket payment, passenger details and seat spot code reserved, defined with unique ticket code.
         */
        new FlightTicket("CODE", flight, ticketPayment, passengerDetail, seat.getSpotCode());

        /*
         * DataInitializer API and DataInıtializer Service creates some examples and saving them to db. You can deactivate them.
         *
         * See how APIs working with examples:
         * https://www.postman.com/icanseker/workspace/airlinesticketproject/overview
         */
    }
}
