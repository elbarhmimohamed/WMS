package com.wms.repository;

import com.wms.model.emplacement.Emplacement;
import com.wms.model.operation.Inventaire;
import com.wms.model.personne.Users;
import com.wms.model.stock.Composante;
import com.wms.model.stock.Inventaire_composante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public interface InventaireRepository  extends JpaRepository<Inventaire, Long> {

    @Query("SELECT u FROM Inventaire u WHERE u.id = ?1")
    public Inventaire findInventaireByID(long id);



    @Query("SELECT u FROM Inventaire u WHERE u.reference = ?1")
    public Inventaire findInventaireByRef(String ref);

    @Modifying
    @Query("UPDATE Inventaire u SET u.date = ?2 WHERE u.id = ?1 ")
    public void updateDateofInventaire( Long id , Date date );

    @Modifying
    @Query("UPDATE Inventaire u SET u.user = ?2 WHERE u.id = ?1 ")
    public void updateUsreofInventaire( Long id , Users user );

    @Modifying
    @Query("UPDATE Inventaire u SET u.inventaire_composantes = ?2 WHERE u.id = ?1 ")
    public void updateInvCompofInventaire(Long id , List<Inventaire_composante> inventaire_composante );
}
