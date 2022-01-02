package com.wms.repository;

import com.wms.model.operation.Operation;
import com.wms.model.operation.Transport;
import com.wms.model.personne.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation,Long> {

    @Query("SELECT u FROM Operation u WHERE u.type = true ")
    public Iterable<Operation> findAllExpedition();

    @Query("SELECT u FROM Operation u WHERE u.type = false ")
    public Iterable<Operation> findAllReception();
}
