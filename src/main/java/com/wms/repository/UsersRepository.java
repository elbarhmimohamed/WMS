package com.wms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wms.model.personne.Users;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;


@Transactional
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("SELECT u FROM Users u WHERE u.id = ?1")
    public Users findByID(long id);

    @Query("SELECT u FROM Users u WHERE u.email = ?1")
    public Users findByEmail(String email);

    @Query("SELECT u FROM Users u WHERE u.name = ?1")
    public Users findByName(String name);


    @Modifying
    @Query("UPDATE Users u SET u.name = ?2 WHERE u.id = ?1 ")
    public void updateNameofUser( Long id , String name );

    @Modifying
    @Query("UPDATE Users u SET u.email = ?2 WHERE u.id = ?1")
    public void updateEmailofUser(Long id, String email );

    @Modifying
    @Query("UPDATE Users u SET u.role = ?2 WHERE u.id = ?1")
    public void updateRoleofUser(Long id, String role );

    @Modifying
    @Query("UPDATE Users u SET u.password = ?2 WHERE u.id = ?1")
    public void updatePAsswordofUser(Long id, String password );

    @Modifying
    @Query("UPDATE Users u SET u.editing_date = ?2 WHERE u.id = ?1")
    public void updateEditingDateofUser(Long id, LocalDateTime date );



}
