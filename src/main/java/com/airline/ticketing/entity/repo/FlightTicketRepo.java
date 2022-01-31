package com.airline.ticketing.entity.repo;

import com.airline.ticketing.entity.ticket.FlightTicketEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Qualifier("flight-ticket-r")
public interface FlightTicketRepo extends CrudRepository<FlightTicketEntity, UUID> {
    FlightTicketEntity findByTicketCode(UUID ticketCode);
}
