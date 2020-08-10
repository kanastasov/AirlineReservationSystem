package com.kirilanastasoff.ars.model.airplane;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kirilanastasoff.ars.model.customer.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "seat_number")
	private int seatNumber;

	@Column(name = "is_cancellable")
	private boolean isCancellable;
	
	@Column(name = "journey_date")
	private LocalDate journeyDate;
	@Column(name = "customer")
	private Customer customer;

}
