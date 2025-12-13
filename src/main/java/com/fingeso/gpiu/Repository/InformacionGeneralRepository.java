package com.fingeso.gpiu.Repository;

import com.fingeso.gpiu.Entity.InformacionGeneralEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InformacionGeneralRepository extends JpaRepository<InformacionGeneralEntity, Long> {
    List<InformacionGeneralEntity> findAllByOrderByOrdenVisualizacionAsc();
}