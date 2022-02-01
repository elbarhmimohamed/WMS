package com.wms.repository;

import com.wms.model.personne.Users;
import com.wms.model.stock.Categorie;
import com.wms.model.stock.Composante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface ComposantRepository extends JpaRepository<Composante, Long> {

    @Query("SELECT u FROM Composante u WHERE u.name = ?1")
    public Optional<Composante> findComposanteByName(String name);


    @Modifying
    @Query("UPDATE Composante u SET u.name = ?2 WHERE u.id = ?1 ")
    public void updateNameofComposante( Long id , String name );

    @Modifying
    @Query("UPDATE Composante u SET u.quantity = ?2 WHERE u.id = ?1 ")
    public void updateQuantityofComposante( Long id , long qte );

    @Modifying
    @Query("UPDATE Composante u SET u.seuil = ?2 WHERE u.id = ?1 ")
    public void updateSeuilofComposante( Long id , long seuil );

    @Modifying
    @Query("UPDATE Composante u SET u.categorie = ?2 WHERE u.id = ?1 ")
    public void updateCatofComposante( Long id , Categorie cat );
}
