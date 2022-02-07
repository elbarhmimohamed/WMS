package com.wms.repository;

import com.wms.model.emplacement.Emplacement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface EmplacementRepository extends JpaRepository<Emplacement, Long> {

    @Query("SELECT u FROM Emplacement u WHERE u.refemplacement = ?1")
    public Emplacement findByrefemplacement(String refemplacement);

    boolean existsByRefemplacement(String refemplacement);

    @Query("SELECT u FROM Emplacement u WHERE u.id = ?1")
    public Emplacement findByID(long id);

    @Modifying
    @Query("UPDATE Emplacement u SET u.refemplacement = ?2 WHERE u.id = ?1")
    public void updateRefofEmplacement(Long id, String ref );

}
