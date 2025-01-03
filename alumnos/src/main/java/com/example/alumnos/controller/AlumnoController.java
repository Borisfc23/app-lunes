package com.example.alumnos.controller;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import com.example.alumnos.entity.AlumnoDTO;
import com.example.alumnos.repository.AlumnoRepository;
import com.example.alumnos.service.AlumnoService;

import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
	@Autowired
	AlumnoRepository alumnorepo;
	
	@Autowired
    private AlumnoService alumnosService;

	@PostMapping("/create")
	public ResponseEntity<?> guardar(@RequestBody AlumnoDTO requestBody) {
		alumnosService.enviarYGuardarAlumno(requestBody);
        return ResponseEntity.ok(Map.of("status", "ok"));
	}

	
	@GetMapping("/all")
	public  List<Map<String, Object>> obtenerTodos() {
		return alumnorepo.listarAlumnosSP();
	}
}
