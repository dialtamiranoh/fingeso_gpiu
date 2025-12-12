package com.fingeso.gpiu.Controller;

import com.fingeso.gpiu.Entity.PiuEntity;
import com.fingeso.gpiu.Service.PiuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/piu")
@CrossOrigin("*")
public class PiuController {

    @Autowired
    private PiuService piuService;

    // CU-PIU: Obtener todos los PIUs activos
    @GetMapping
    public ResponseEntity<List<PiuEntity>> obtenerPiusActivos() {
        List<PiuEntity> pius = piuService.obtenerPiusActivos();
        return ResponseEntity.ok(pius);
    }

    // CU-PIU: Obtener PIU más cercano
    @GetMapping("/cercano")
    public ResponseEntity<PiuEntity> obtenerPiuCercano(
            @RequestParam Double lat,
            @RequestParam Double lon) {
        PiuEntity piu = piuService.encontrarPiuCercano(lat, lon);
        return ResponseEntity.ok(piu);
    }

    // Obtener PIU por ID
    @GetMapping("/{id}")
    public ResponseEntity<PiuEntity> obtenerPiuPorId(@PathVariable Integer id) {
        PiuEntity piu = piuService.obtenerPiuPorId(id);
        return ResponseEntity.ok(piu);
    }
}