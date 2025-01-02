package com.example.alumnos.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.alumnos.entity.Alumnos;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumnos,Long>{

}
