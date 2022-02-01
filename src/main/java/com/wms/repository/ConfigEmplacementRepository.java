package com.wms.repository;

import com.wms.model.emplacement.ConfigEmplacement;
import com.wms.model.personne.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigEmplacementRepository extends JpaRepository<ConfigEmplacement,Long> {

}
