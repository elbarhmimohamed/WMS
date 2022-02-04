package com.wms.repository;


import com.wms.model.operation.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {

    @Query("SELECT u FROM Transport u WHERE u.matricule = ?1 ")
    public Transport findTransportByMatricule(String matricule);

    @Query("SELECT u FROM Transport u WHERE u.id = ?1 ")
    public Transport findTransportById(long id);
}
