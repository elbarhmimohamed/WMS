package com.wms.repository;

import com.wms.model.operation.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommande,Integer> {
}


