package com.example.alumnos.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExternalApiResponse {
	private Long id;  
	private String nombre;
    private String apellido;
    public String getMensajeFormat() {
        return "{"+"id=" + id + ", nombre=" + nombre + ", apellido=" + apellido+"}";
    }

}
