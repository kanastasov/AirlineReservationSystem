package com.kirilanastasoff.ars.repository.airplane;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kirilanastasoff.ars.model.airplane.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
