package com.kirilanastasoff.ars.repository.airplane;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kirilanastasoff.ars.model.airplane.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
