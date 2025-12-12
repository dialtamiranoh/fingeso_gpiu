package com.fingeso.gpiu.Controller;

import com.fingeso.gpiu.Entity.HorarioEntity;
import com.fingeso.gpiu.Service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/horarios")
@CrossOrigin("*")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    // CU-004: Obtener horarios de alumno
    @GetMapping("/alumno/{id}")
    public ResponseEntity<List<HorarioEntity>> obtenerHorariosAlumno(@PathVariable Integer id) {
        List<HorarioEntity> horarios = horarioService.obtenerHorariosPorAlumno(id);
        return ResponseEntity.ok(horarios);
    }

    // CU-004: Obtener horarios de profesor
    @GetMapping("/profesor/{id}")
    public ResponseEntity<List<HorarioEntity>> obtenerHorariosProfesor(@PathVariable Integer id) {
        List<HorarioEntity> horarios = horarioService.obtenerHorariosPorProfesor(id);
        return ResponseEntity.ok(horarios);
    }

    // CU-004: Obtener horario semanal de alumno
    @GetMapping("/alumno/{id}/semanal")
    public ResponseEntity<Map<String, List<HorarioEntity>>> obtenerHorarioSemanalAlumno(@PathVariable Integer id) {
        Map<String, List<HorarioEntity>> horarioSemanal = horarioService.obtenerHorarioSemanalAlumno(id);
        return ResponseEntity.ok(horarioSemanal);
    }

    // CU-004: Obtener horario semanal de profesor
    @GetMapping("/profesor/{id}/semanal")
    public ResponseEntity<Map<String, List<HorarioEntity>>> obtenerHorarioSemanalProfesor(@PathVariable Integer id) {
        Map<String, List<HorarioEntity>> horarioSemanal = horarioService.obtenerHorarioSemanalProfesor(id);
        return ResponseEntity.ok(horarioSemanal);
    }
}