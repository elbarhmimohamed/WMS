package com.wms.repository;

import com.wms.model.personne.Users;
import com.wms.model.stock.Composante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComposantRepository extends JpaRepository<Composante, Long> {
    @Query("SELECT u FROM Composante u WHERE u.name = ?1")
    public Optional<Composante> findComposanteByName(String name);
}
