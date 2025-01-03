package com.synopsis.capacitacion.customer.entities;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
	private long id;
	private String reference;
	private String accountIban;
	private LocalDateTime date;
	private Double amount;
	private Double fee;
	private String description;
	private String status;
	private String channel;
}
