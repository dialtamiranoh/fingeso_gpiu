package com.fingeso.gpiu.Service;

import com.fingeso.gpiu.Entity.PiuEntity;
import com.fingeso.gpiu.Repository.PiuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PiuService {

    private final PiuRepository piuRepository;

    @Autowired
    public PiuService(PiuRepository piuRepository) {
        this.piuRepository = piuRepository;
    }

    // CU-PIU: Obtener PIUs activos
    public List<PiuEntity> obtenerPiusActivos() {
        return piuRepository.findByEstado("ACTIVO");
    }

    // CU-PIU: Encontrar PIU más cercano
    public PiuEntity encontrarPiuCercano(Double latUsuario, Double lonUsuario) {
        List<PiuEntity> pius = piuRepository.findByEstado("ACTIVO");

        return pius.stream()
                .min(Comparator.comparingDouble(piu ->
                        calcularDistancia(latUsuario, lonUsuario,
                                piu.getLatitud(), piu.getLongitud())
                ))
                .orElseThrow(() -> new RuntimeException("No hay PIUs cercanos"));
    }

    private Double calcularDistancia(Double lat1, Double lon1, Double lat2, Double lon2) {
        final int R = 6371; // Radio Tierra en km

        Double latDistance = Math.toRadians(lat2 - lat1);
        Double lonDistance = Math.toRadians(lon2 - lon1);

        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c * 1000; // metros
    }

    public List<PiuEntity> obtenerTodosPiu() {
        return piuRepository.findAll();
    }

    public PiuEntity obtenerPiuPorId(Integer id) {
        return piuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PIU no encontrado"));
    }
}