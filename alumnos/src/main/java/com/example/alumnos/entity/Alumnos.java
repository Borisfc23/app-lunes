package com.example.alumnos.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Alumnos {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String mensaje;
	
	private LocalDateTime fechahora;
}
