package com.wms.repository;
import com.wms.model.emplacement.ConfigEmplacement;
import com.wms.model.operation.ControleQualite;
import com.wms.model.operation.Reception;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ControleQualiteRepository extends JpaRepository<ControleQualite,Long> {
    List<ControleQualite> findAllByReception(Reception reception);

}
