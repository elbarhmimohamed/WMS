package com.wms.repository;
import com.wms.model.emplacement.ConfigEmplacement;
import com.wms.model.operation.Reception;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ReceptionRepository extends JpaRepository<Reception,Integer> {


}
