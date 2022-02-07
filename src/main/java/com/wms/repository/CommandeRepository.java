package com.wms.repository;

import com.wms.model.emplacement.ConfigEmplacement;
import com.wms.model.operation.Commande;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Transactional
@Repository
public interface CommandeRepository extends JpaRepository<Commande,Integer > {




}