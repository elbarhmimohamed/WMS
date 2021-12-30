package com.wms.repository;

import com.wms.model.personne.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wms.model.personne.Users;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {


   // @Query("SELECT u FROM Person u WHERE u.name = ?1")
   // public Users findByEmail(String email);


}