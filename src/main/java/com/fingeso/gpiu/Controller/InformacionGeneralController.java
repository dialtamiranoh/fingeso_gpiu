package com.fingeso.gpiu.Controller;

import com.fingeso.gpiu.Entity.InformacionGeneralEntity;
import com.fingeso.gpiu.Service.InformacionGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/informacion-general")
@CrossOrigin(origins = "http://localhost:5173")
public class InformacionGeneralController {

    @Autowired
    private InformacionGeneralService informacionGeneralService;

    @GetMapping
    public ResponseEntity<List<InformacionGeneralEntity>> obtenerTodas() {
        return ResponseEntity.ok(informacionGeneralService.obtenerTodas());
    }
}