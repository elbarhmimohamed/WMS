package com.wms.repository;

import com.wms.model.personne.Users;
import com.wms.model.stock.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
=======
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

>>>>>>> f2b7a46a917d3335c2b16ea6dd0df5d10ac97fd4
@Repository
public interface CategoriesRepository extends JpaRepository<Categorie,Long> {

    @Query("SELECT u FROM Categorie u WHERE u.name = ?1")
    public Optional<Categorie> findCategorieByName(String name);

<<<<<<< HEAD
    @Modifying
    @Query("UPDATE Categorie u SET u.name = ?2 WHERE u.id = ?1 ")
    public void updateNameofCat( Long id , String name );

    @Modifying
    @Query("UPDATE Categorie u SET u.description = ?2 WHERE u.id = ?1 ")
    public void updateDescofCat( Long id , String desc );

=======
>>>>>>> f2b7a46a917d3335c2b16ea6dd0df5d10ac97fd4
}
