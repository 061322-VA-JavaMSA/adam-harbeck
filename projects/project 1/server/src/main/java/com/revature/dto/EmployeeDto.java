package com.revature.dto;

import java.util.Objects;
import java.util.UUID;

import com.revature.models.Employee;
import com.revature.models.Role;

public class EmployeeDto {

	private UUID id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private Role role;
	
	public EmployeeDto(Employee e) {
		id = e.getId();
		username = e.getUsername();
		firstName = e.getFirstName();
		lastName = e.getLastName();
		email = e.getEmail();
		role = e.getRole();
	}

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
		return Objects.hash(email, firstName, id, lastName, role, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeDto other = (EmployeeDto) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName) && role == other.role
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "EmployeeDto [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", role=" + role + "]";
	}
	
	
}
