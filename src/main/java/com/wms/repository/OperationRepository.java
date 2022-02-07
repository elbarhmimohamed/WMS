package com.wms.repository;

import com.wms.model.operation.Commande;
import com.wms.model.operation.Operation;
import com.wms.model.operation.Transport;
import com.wms.model.personne.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation,Long> {

    @Query("SELECT u FROM Operation u WHERE u.type = true ")
    public Iterable<Operation> findAllExpedition();

    @Query("SELECT u FROM Operation u WHERE u.id = ?1 ")
    public Operation findOperationById(long id);

    @Query("SELECT u FROM Operation u WHERE u.type = false ")
    public Iterable<Operation> findAllReception();

    @Modifying
    @Query("UPDATE Operation u SET u.date = ?2 WHERE u.date = ?1 ")
    public void updateDateOfOperation( Long id , Date date );

    @Modifying
    @Query("UPDATE Operation u SET u.commande = ?2 WHERE u.date = ?1 ")
    public void updatecommandeOfOperation( Long id , Commande cmd );

    @Modifying
    @Query("UPDATE Operation u SET u.transport = ?2 WHERE u.date = ?1 ")
    public void updateTransportOfOperation( Long id , Transport transport );
}
