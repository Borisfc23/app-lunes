package com.example.alumnos.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.alumnos.entity.AlumnoDTO;
import com.example.alumnos.entity.Alumnos;
import com.example.alumnos.entity.ExternalApiResponse;
import com.example.alumnos.repository.AlumnoRepository;

import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AlumnoService {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Environment env;

	@Autowired
	private AlumnoRepository alumnosRepository;

	private static final String EXTERNAL_API_URL = "https://jsonplaceholder.typicode.com/posts";

	public void enviarYGuardarAlumno(AlumnoDTO request) {

		ExternalApiResponse response = restTemplate.postForObject(env.getProperty("endpoint.ms-alumnos.base-path"), request, ExternalApiResponse.class);

		Alumnos alumno = new Alumnos();
		alumno.setMensaje(response.getMensajeFormat());
		alumno.setFechahora(LocalDateTime.now());

		alumnosRepository.save(alumno);
	}
}
