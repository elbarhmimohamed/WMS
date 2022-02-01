package com.wms.repository;

import com.wms.model.operation.Commande;
<<<<<<< HEAD
import com.wms.model.personne.Person;
import com.wms.model.personne.Users;
import com.wms.model.stock.Composante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;

@Transactional
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

>>>>>>> f2b7a46a917d3335c2b16ea6dd0df5d10ac97fd4
@Repository
public interface CommandeRepository extends JpaRepository<Commande,Long> {

    @Query("SELECT u FROM Commande u WHERE u.type = true ")
    public Iterable<Commande> findAllExpeditionCommande();

    @Query("SELECT u FROM Commande u WHERE u.type = false ")
    public Iterable<Commande> findAllReceptionCommande();
<<<<<<< HEAD

    @Modifying
    @Query("UPDATE Commande u SET u.date = ?2 WHERE u.id = ?1")
    public void updateDateofCmd(Long id, Date date );

    @Modifying
    @Query("UPDATE Commande u SET u.type = ?2 WHERE u.id = ?1")
    public void updateTypeofCmd(Long id, boolean type );

    @Modifying
    @Query("UPDATE Commande u SET u.user = ?2 WHERE u.id = ?1")
    public void updateUserofCmd(Long id, Users users );

    @Modifying
    @Query("UPDATE Commande u SET u.person = ?2 WHERE u.id = ?1")
    public void updatePersonofCmd(Long id, Person person );

    @Modifying
    @Query("UPDATE Commande u SET u.composantes = ?2 WHERE u.id = ?1")
    public void updateComposantesofCmd(Long id, Collection<Composante> composantes );

=======
>>>>>>> f2b7a46a917d3335c2b16ea6dd0df5d10ac97fd4
}
