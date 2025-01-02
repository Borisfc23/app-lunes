package com.example.alumnos.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.alumnos.entity.Alumnos;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumnos, Long> {
	
	@Query(value = "SELECT * FROM listar_alumnos()", nativeQuery = true)
	List<Map<String, Object>> listarAlumnosSP();
}
