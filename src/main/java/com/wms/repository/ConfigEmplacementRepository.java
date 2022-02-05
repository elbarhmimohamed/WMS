package com.wms.repository;

import com.wms.model.emplacement.ConfigEmplacement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConfigEmplacementRepository extends JpaRepository<ConfigEmplacement,Long> {
    ConfigEmplacement findByIndexRangee(String indexRangee);

    @Modifying
    @Query("UPDATE ConfigEmplacement c SET c.occupation =0 where  1=1")
    void libérerToutRangee();

    @Modifying
    @Query("select c.indexRangee from ConfigEmplacement c where  1=1")
    List<String> findallrangee();


}
