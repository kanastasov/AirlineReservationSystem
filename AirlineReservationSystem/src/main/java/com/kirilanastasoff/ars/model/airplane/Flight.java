package com.kirilanastasoff.ars.model.airplane;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


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
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	@Column(name = "face")
	private int fare;
	
	@Column(name = "journey_time")
	private int journeyTime;
	
	@Column(name = "airplanes")
	private Airplanes airplanes;
	
	@Column(name = "american_sirlines")
	private AmericanAirlines americanAirlines;
	
	@Column(name = "source_stop")
	private Stop sourceStop;
	
	@Column(name = "dest_stop")
	private Stop destStop;
	
	
	
	
}
