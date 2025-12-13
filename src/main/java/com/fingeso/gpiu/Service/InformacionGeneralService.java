package com.fingeso.gpiu.Service;

import com.fingeso.gpiu.Entity.InformacionGeneralEntity;
import com.fingeso.gpiu.Repository.InformacionGeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InformacionGeneralService {

    @Autowired
    private InformacionGeneralRepository informacionGeneralRepository;

    public List<InformacionGeneralEntity> obtenerTodas() {
        return informacionGeneralRepository.findAllByOrderByOrdenVisualizacionAsc();
    }
}