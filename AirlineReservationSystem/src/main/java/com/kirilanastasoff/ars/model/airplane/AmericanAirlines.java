package com.kirilanastasoff.ars.model.airplane;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.kirilanastasoff.ars.model.customer.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "american_airlines")
public class AmericanAirlines {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "details")
	private String details;
	
	@Column(name = "customer")
	@Transient
	private Customer customer;
	
//	@Column(name = "list_of_airplanes")
	@OneToMany(targetEntity = Airplanes.class, mappedBy = "americanAirlines", cascade =CascadeType.ALL)
	private List<Airplanes> listOfAirplanes = new ArrayList<>();
	
	public void addAirplanes(Airplanes airplane) {
		listOfAirplanes.add(airplane);
		airplane.setAmericanAirlines(this);
	}
	
	public void removeAirplanes(Airplanes airplane) {
		listOfAirplanes.remove(airplane);
		airplane.setAmericanAirlines(null);
	}
	
}
