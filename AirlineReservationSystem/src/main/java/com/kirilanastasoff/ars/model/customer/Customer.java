package com.kirilanastasoff.ars.model.customer;

import java.util.Set;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import com.kirilanastasoff.ars.model.airplane.Airplanes;
import com.kirilanastasoff.ars.model.airplane.AmericanAirlines;
import com.kirilanastasoff.ars.model.airplane.Ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty(message = "Please type your first name")
	@Length(min = 3, message = "Your first name need to be at least 3 characters")
	@Column(name = "first_name")
	private String firstName;

	@NotEmpty(message = "Please type your last name")
	@Length(min = 3, message = "Your last name need to be at least 3 characters")
	@Column(name = "last_name")
	private String lastName;
	
	@NotEmpty(message = "Please type your email")
	@Column(name = "email")
	@Length(min = 5, message = "Your email need to be at least 5 characters")
	@Email
	private String email;
	
	@NotEmpty(message = "Please type your passsword")
	@Length(min = 5, message = "Your password need to be at least 5 characters")
	@Column(name = "password")
	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//	inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles;
	
	@NotEmpty(message = "Please type your username")
	@Length(min = 5, message = "Your username need to be at least 5 characters")
	@Column(name = "username")
	private String username;
	
	@OneToOne(targetEntity = AmericanAirlines.class, mappedBy = "customer", cascade =CascadeType.ALL)
	private AmericanAirlines americanAirlines;
	
	@OneToOne(targetEntity = Ticket.class, mappedBy = "customer", cascade =CascadeType.ALL)
	private Ticket ticket;
	
	@Column(name = "enabled")
	private Boolean enabled;
	
	public void addAmericanAirlines(AmericanAirlines americanAirlines) {
		this.americanAirlines = americanAirlines;
		americanAirlines.setCustomer(this);
	}
	
	public void removeAmericanAirlines(AmericanAirlines airplane) {
		this.americanAirlines = null;
		americanAirlines.setCustomer(null);
	}
	
	public void addTicket(Ticket ticket) {
		this.ticket = ticket;
		ticket.setCustomer(this);
	}
	
	public void removeTicket(Ticket ticket) {
		this.ticket = null;
		ticket.setCustomer(null);
	}
	
	
}
