package com.revature.models;



import java.sql.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table ( name = "tickets", schema = "public")
public class Ticket {
	@Id
	@Column( name="id")
	private UUID id;
	@Column( name="amount", nullable = false, columnDefinition="NUMERIC")
	private double amount;
	@Column( name="submitted", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern ="yyy-MM-dd")
	private Date submitted;
	@Column( name="description", nullable = false)
	private String description;
	@Column( name="employee", nullable = false)
	private UUID author;
	@Column( name="approved_by")
	private UUID approvedBy;
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private Status status;
	@Column(name="reimb_type")
	@Enumerated(EnumType.STRING)
	private ReimbType type;

	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double d) {
		this.amount = d;
	}
	public Date getSubmitted() {
		return submitted;
	}
	public void setSubmitted(Date submitted) {
		this.submitted = submitted;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UUID getAuthor() {
		return author;
	}
	public void setAuthor(UUID author) {
		this.author = author;
	}
	public UUID getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(UUID approvedBy) {
		this.approvedBy = approvedBy;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public ReimbType getType() {
		return type;
	}
	public void setType(ReimbType type) {
		this.type = type;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(amount, approvedBy, author, description, id, status, submitted, type);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(approvedBy, other.approvedBy) && Objects.equals(author, other.author)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& status == other.status && Objects.equals(submitted, other.submitted) && type == other.type;
	}
	
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", description=" + description
				+ ", author=" + author + ", approvedBy=" + approvedBy + ", status=" + status + ", type=" + type + "]";
	}
	
}
