package com.kirilanastasoff.ars.model.airplane;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flight_schedule")
public class FlightSchedule {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
//	@Column(name = "flight")
	@Transient
	private Flight flight;
	
	@Column(name = "trip_date")
	private LocalDate tripDate;
	
	@Column(name = "available_seats")
	private int availableSeats;
	
	@OneToMany(targetEntity = Ticket.class, mappedBy = "flightSchedule", cascade = CascadeType.ALL)
	private List<Ticket> ticketsSold = new ArrayList<>();
	
	public void addTicket(Ticket ticket) {
		ticketsSold.add(ticket);
		ticket.setFlightSchedule(this);
	}
	
	public void removeTicket(Ticket ticket) {
		ticketsSold.remove(ticket);
		ticket.setFlightSchedule(null);
	}
}
