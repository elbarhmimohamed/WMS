package com.wms.repository;

import com.wms.model.operation.Inventaire;
import com.wms.model.stock.Composante;
import com.wms.model.stock.Inventaire_composante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface Inventaire_composanteRepository  extends JpaRepository<Inventaire_composante, Long> {

    @Query("SELECT u FROM Inventaire_composante u WHERE u.id = ?1")
    public Inventaire_composante findInventaire_composanteByID(long id);

    @Modifying
    @Query("UPDATE Inventaire_composante u SET u.composante = ?2 WHERE u.id = ?1 ")
    public void updateComposanteofInventaire_composante( Long id , Composante composante );

    @Modifying
    @Query("UPDATE Inventaire_composante u SET u.quantityInReality = ?2 WHERE u.id = ?1 AND ?2 > 0 ")
    public void updateQteinRealityofInventaire_composante( Long id , long qte );


    @Modifying
    @Query("UPDATE Inventaire_composante u SET u.Ecart = ?2 WHERE u.id = ?1 AND ?2 > 0 ")
    public void updateEcartofInventaire_composante( Long id , long ecart );

    @Modifying
    @Query("UPDATE Inventaire_composante u SET u.inventaire = ?2 WHERE u.id = ?1  ")
    public void updateInventaireofInventaire_composante( Long id , Inventaire inventaire );



}
