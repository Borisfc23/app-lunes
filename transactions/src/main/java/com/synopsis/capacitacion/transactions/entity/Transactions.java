package com.synopsis.capacitacion.transactions.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Transactions {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String reference;
	
	@Column(name = "accountiban") 
	private String accountIban;
	private LocalDateTime date;
	private Double amount;
	private Double fee;
	private String description;
	private String status;
	private String channel;
}
