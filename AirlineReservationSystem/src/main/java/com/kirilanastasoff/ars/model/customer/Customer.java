package com.kirilanastasoff.ars.model.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Size(min = 1, max = 15)
	@NonNull
	@Column(name = "first_name")
	private String firstName;
	
	@Size(min = 1, max = 15)
	@NonNull
	@Column(name = "last_name")
	private String lastName;
	
	@NonNull
	@Column(name = "email")
	@Email
	private String email;
	
	@NonNull
	@Column(name = "password")
	private  String password;
	
	
}
