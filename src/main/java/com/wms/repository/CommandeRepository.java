package com.wms.repository;

import com.wms.model.emplacement.ConfigEmplacement;
import com.wms.model.operation.Commande;

import com.wms.model.operation.Inventaire;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Transactional
@Repository
public interface CommandeRepository extends JpaRepository<Commande,Integer > {

    @Query("SELECT u FROM Commande u WHERE u.id = ?1")
    public Commande findCommandeBy(int id);




}
