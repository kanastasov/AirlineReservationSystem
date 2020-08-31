package com.kirilanastasoff.ars.model.airplane;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "airplanes")
public class Airplanes {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "a_id")
	private Long aId;
	
	@Column(name = "a_code")
	private String aCode;
	
	@Column(name = "capacity")
	private int capacity;
	
	@Column(name = "make")
	private String make;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "american_airlines_id", referencedColumnName = "aa_id")
	private AmericanAirlines americanAirlines;
	
	@OneToOne(targetEntity = Flight.class, mappedBy = "airplanes", cascade =CascadeType.ALL)
	private Flight flight;
	
	public void addFlight(Flight flight) {
		this.flight = flight;
		flight.setAirplanes(this);
	}
	
}
