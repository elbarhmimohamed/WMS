package com.wms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wms.model.personne.Users;


@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {

}
