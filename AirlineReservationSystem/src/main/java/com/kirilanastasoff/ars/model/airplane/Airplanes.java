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
@Table(name = "airplanes")
public class Airplanes {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "capacity")
	private int capacity;
	
	@Column(name = "make")
	private String make;
	
	@Column(name = "american_airlines")
	private AmericanAirlines americanAirlines;
}