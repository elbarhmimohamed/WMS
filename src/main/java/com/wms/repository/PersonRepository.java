package com.wms.repository;

import com.wms.model.personne.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT u FROM Person u WHERE u.name = ?1 ")
<<<<<<< HEAD
    public Optional<Person> findPersonByName(String name);

    @Query("SELECT u FROM Person u WHERE u.role = true ")
    public Iterable<Person> findAllCustomers();

    @Query("SELECT u FROM Person u WHERE u.role = false ")
    public Iterable<Person> findAllSuppliers();

    @Modifying
    @Query("UPDATE Person u SET u.name = ?2 WHERE u.id = ?1 ")
    public void updateNameofPerson( Long id , String name );

    @Modifying
    @Query("UPDATE Person u SET u.mail = ?2 WHERE u.id = ?1 ")
    public void updateMailofPerson( Long id , String mail );

    @Modifying
    @Query("UPDATE Person u SET u.adress = ?2 WHERE u.id = ?1 ")
    public void updateAdressofPerson( Long id , String add );

    @Modifying
    @Query("UPDATE Person u SET u.phone = ?2 WHERE u.id = ?1 ")
    public void updatePhoneofPerson( Long id , String phone );

    @Modifying
    @Query("UPDATE Person u SET u.status = ?2 WHERE u.id = ?1 ")
    public void updateStatusofPerson( Long id , boolean status );
=======
    public Person findPersonByName(String name);

    @Query("SELECT u FROM Person u WHERE u.role = true ")
    public Iterable<Person> findAllCustomers();

    @Query("SELECT u FROM Person u WHERE u.role = false ")
    public Iterable<Person> findAllSuppliers();
>>>>>>> f2b7a46a917d3335c2b16ea6dd0df5d10ac97fd4


}