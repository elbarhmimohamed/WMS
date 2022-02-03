package com.wms.repository;

import com.wms.model.personne.Users;
import com.wms.model.stock.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional

@Repository
public interface CategoriesRepository extends JpaRepository<Categorie,Long> {

    @Query("SELECT u FROM Categorie u WHERE u.categorie_name = ?1")
    public Categorie findCategorieByName(String name);


    @Modifying
    @Query("UPDATE Categorie u SET u.categorie_name = ?2 WHERE u.id = ?1 ")
    public void updateNameofCat( Long id , String name );

    @Modifying
    @Query("UPDATE Categorie u SET u.categorie_description = ?2 WHERE u.id = ?1 ")
    public void updateDescofCat( Long id , String desc );

}
