package com.wms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wms.model.personne.Users;


@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {


    @Query("SELECT u FROM Users u WHERE u.email = ?1")
    public Users findByEmail(String email);

    @Query("SELECT u FROM Users u WHERE u.Name = ?1")
    public Users findByName(String name);
}
