package com.kirilanastasoff.ars.model.airplane;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "fare")
	private int fare;
	
	@Column(name = "journey_time")
	private int journeyTime;
	
//	@Column(name = "airplanes")
	@Transient
	private Airplanes airplanes;
	
//	@Column(name = "american_airlines")
	@Transient
	private AmericanAirlines americanAirlines;
	
//	@Column(name = "source_stop")
	@Transient
	private Stop sourceStop;
	
//	@Column(name = "dest_stop")
	@Transient
	private Stop destStop;
	
	
	
	
}
