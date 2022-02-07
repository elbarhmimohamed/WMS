package com.wms.repository;


import com.wms.model.operation.FichierStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FichierStockRepository extends JpaRepository<FichierStock, Integer> {
}
