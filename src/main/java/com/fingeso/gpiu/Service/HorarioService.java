package com.fingeso.gpiu.Service;

import com.fingeso.gpiu.Entity.HorarioEntity;
import com.fingeso.gpiu.Repository.AlumnoRepository;
import com.fingeso.gpiu.Repository.HorarioRepository;
import com.fingeso.gpiu.Repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class HorarioService {

    private final HorarioRepository horarioRepository;
    private final AlumnoRepository alumnoRepository;
    private final ProfesorRepository profesorRepository;

    @Autowired
    public HorarioService(HorarioRepository horarioRepository,
                          AlumnoRepository alumnoRepository,
                          ProfesorRepository profesorRepository) {
        this.horarioRepository = horarioRepository;
        this.alumnoRepository = alumnoRepository;
        this.profesorRepository = profesorRepository;
    }

    // CU-004: Consultar horarios de alumno
    public List<HorarioEntity> obtenerHorariosPorAlumno(Integer idAlumno) {
        List<HorarioEntity> horarios = horarioRepository.findByAlumnoId(idAlumno);
        horarios.sort(Comparator.comparing(HorarioEntity::getDia)
                .thenComparing(HorarioEntity::getHoraInicio));
        return horarios;
    }

    // CU-004: Consultar horarios de profesor
    public List<HorarioEntity> obtenerHorariosPorProfesor(Integer idProfesor) {
        List<HorarioEntity> horarios = horarioRepository.findByProfesorId(idProfesor);
        horarios.sort(Comparator.comparing(HorarioEntity::getDia)
                .thenComparing(HorarioEntity::getHoraInicio));
        return horarios;
    }

    public List<HorarioEntity> obtenerHorariosPorCurso(Integer idCurso) {
        return horarioRepository.findByCursoIdCurso(idCurso);
    }

    public List<HorarioEntity> obtenerHorariosPorDia(String dia) {
        return horarioRepository.findByDia(dia);
    }

    public Map<String, List<HorarioEntity>> obtenerHorarioSemanalAlumno(Integer idAlumno) {
        List<HorarioEntity> horarios = horarioRepository.findByAlumnoId(idAlumno);

        Map<String, List<HorarioEntity>> horarioSemanal = new LinkedHashMap<>();
        horarioSemanal.put("LUNES", new ArrayList<>());
        horarioSemanal.put("MARTES", new ArrayList<>());
        horarioSemanal.put("MIERCOLES", new ArrayList<>());
        horarioSemanal.put("JUEVES", new ArrayList<>());
        horarioSemanal.put("VIERNES", new ArrayList<>());
        horarioSemanal.put("SABADO", new ArrayList<>());

        for (HorarioEntity h : horarios) {
            String dia = h.getDia().toUpperCase();
            if (horarioSemanal.containsKey(dia)) {
                horarioSemanal.get(dia).add(h);
            }
        }

        // Ordenar cada día por hora
        horarioSemanal.values().forEach(lista ->
                lista.sort(Comparator.comparing(HorarioEntity::getHoraInicio))
        );

        return horarioSemanal;
    }

    public Map<String, List<HorarioEntity>> obtenerHorarioSemanalProfesor(Integer idProfesor) {
        List<HorarioEntity> horarios = horarioRepository.findByProfesorId(idProfesor);

        Map<String, List<HorarioEntity>> horarioSemanal = new LinkedHashMap<>();
        horarioSemanal.put("LUNES", new ArrayList<>());
        horarioSemanal.put("MARTES", new ArrayList<>());
        horarioSemanal.put("MIERCOLES", new ArrayList<>());
        horarioSemanal.put("JUEVES", new ArrayList<>());
        horarioSemanal.put("VIERNES", new ArrayList<>());
        horarioSemanal.put("SABADO", new ArrayList<>());

        for (HorarioEntity h : horarios) {
            String dia = h.getDia().toUpperCase();
            if (horarioSemanal.containsKey(dia)) {
                horarioSemanal.get(dia).add(h);
            }
        }

        horarioSemanal.values().forEach(lista ->
                lista.sort(Comparator.comparing(HorarioEntity::getHoraInicio))
        );

        return horarioSemanal;
    }
}