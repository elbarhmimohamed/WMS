package com.wms.repository;

import com.wms.model.operation.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeRepository extends JpaRepository<Commande,Long> {

    @Query("SELECT u FROM Commande u WHERE u.type = true ")
    public Iterable<Commande> findAllExpeditionCommande();

    @Query("SELECT u FROM Commande u WHERE u.type = false ")
    public Iterable<Commande> findAllReceptionCommande();
}
