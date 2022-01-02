package com.wms.repository;

import com.wms.model.emplacement.Emplacement;
import com.wms.model.personne.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmplacementRepository extends JpaRepository<Emplacement, Long> {

    @Query("SELECT u FROM Emplacement u WHERE u.refemplacement = ?1")
    public Emplacement findByrefemplacement(String refemplacement);
}
