package com.revature.models;

import java.time.LocalDate;
import java.util.Objects;

public class Task {

	// Declaring private variables. encapsulation
	private int id;
	private String description;
	private LocalDate dueDate;
	private String status;
	// private int userAssignedId; -- not oop
	private User userAssigned; // OOP
	
	// Declaring a no-args constructor that calls super(0 on the parent class.
	public Task() {
		super();

	}
	
	// Getter and Setters to get and set the values of the variables form within the class.
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public User getUserAssigned() {
		return userAssigned;
	}
	public void setUserAssigned(User userAssigned) {
		this.userAssigned = userAssigned;
	}
	// Overrides hashCode()
	@Override
	public int hashCode() {
		return Objects.hash(description, dueDate, id, status, userAssigned);
	}
	// Overrides .equals() to ensure that the content is checked and not the location in memory.
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(description, other.description) && Objects.equals(dueDate, other.dueDate)
				&& id == other.id && Objects.equals(status, other.status) && Objects.equals(userAssigned,  other.userAssigned);
	}
	// Returns a string representing the object and not an unreadable string
	@Override
	public String toString() {
		return "Task [id=" + id + ", description=" + description + ", dueDate=" + dueDate + ", status=" + status + ", userAssigned=" + userAssigned + "]";
	}
	
	
	
}
