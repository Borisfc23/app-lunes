package com.example.alumnos.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.alumnos.entity.AlumnoDTO;
import com.example.alumnos.entity.Alumnos;
import com.example.alumnos.entity.ExternalApiResponse;
import com.example.alumnos.repository.AlumnoRepository;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AlumnoService {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Environment env;

	@Autowired
	private AlumnoRepository alumnosRepository;
	
	private static final Logger log = LoggerFactory.getLogger(AlumnoService.class);

	private static final String EXTERNAL_API_URL = "https://jsonplaceholder.typicode.com/posts";

	public void enviarYGuardarAlumno(AlumnoDTO request) {
		log.info("Iniciando la tarea...");        
		log.info("El nombre es ..."+request.getNombre());
		log.info("El apellido es ..."+request.getApellido());
		ExternalApiResponse response = restTemplate.postForObject(env.getProperty("endpoint.ms-alumnos.base-path"), request, ExternalApiResponse.class);

		Alumnos alumno = new Alumnos();
		alumno.setMensaje(response.getMensajeFormat());
		alumno.setFechahora(LocalDateTime.now());		
		alumnosRepository.save(alumno);
	}
}
