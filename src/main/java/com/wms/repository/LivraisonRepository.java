package com.wms.repository;

import com.wms.model.operation.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivraisonRepository extends JpaRepository<Livraison,Integer> {
}
