package com.revature.models;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.*;


@Entity
@Table (name = "employees", schema = "public")
public class Employee {
	@Id
	@Column(name="id")
	private UUID id;
	@Column( name="username", nullable = false, unique = true )
	private String username;
	@Column( name="password", nullable = false )
	private String password;
	@Column( name="first_name", nullable = false )
	private String firstName;
	@Column( name="last_name", nullable = false )
	private String lastName;
	@Column( nullable = false, unique = true )
	private String email;
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, lastName, password, role, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password) && role == other.role
				&& Objects.equals(username, other.username);
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", role=" + role + "]";
	}
	
	
}
