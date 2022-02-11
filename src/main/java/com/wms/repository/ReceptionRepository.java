package com.wms.repository;
import com.wms.model.emplacement.ConfigEmplacement;
import com.wms.model.operation.Reception;
import com.wms.model.personne.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ReceptionRepository extends JpaRepository<Reception,Integer> {

    @Query("SELECT u FROM Reception u WHERE u.id = ?1 ")
    public Reception findReceptionByiD(int id);

}
