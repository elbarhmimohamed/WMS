package com.wms.repository;

import com.wms.model.operation.Lignelivraison;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LignelivraisonRepository extends JpaRepository<Lignelivraison, Integer> {
}
