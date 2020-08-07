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
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
