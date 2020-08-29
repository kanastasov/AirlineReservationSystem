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
@Table(name = "flight")
public class Flight {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "fare")
	private int fare;
	
	@Column(name = "journey_time")
	private int journeyTime;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "airplanes_id", referencedColumnName = "id")
	private Airplanes airplanes;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "american_airlines_id", referencedColumnName = "id")
	private AmericanAirlines americanAirlines;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "source_stop_id", referencedColumnName = "id")
	private Stop sourceStop;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "dest_stop_id", referencedColumnName = "id")
	private Stop destStop;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "flight_schedule_id", referencedColumnName = "id")
	private FlightSchedule flightShcedule;
	
	
	
	
}
