package com.wms.repository;

import com.wms.model.personne.Users;
import com.wms.model.stock.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<Categorie,Long> {

    @Query("SELECT u FROM Categorie u WHERE u.name = ?1")
    public Optional<Categorie> findCategorieByName(String name);

}
