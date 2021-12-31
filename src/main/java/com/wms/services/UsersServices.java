package com.wms.services;



import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wms.model.personne.Users;
import com.wms.repository.UsersRepository;



import lombok.Data;

@Data
@Service
public class UsersServices {

    @Autowired
    private UsersRepository usersRepository;

    public Optional<Users> getUser(final Long id) {
        return usersRepository.findById(id);
    }

    public Iterable<Users> getUsers() {
        return usersRepository.findAll();
    }

    public void deleteuser(final Long id) {
        usersRepository.deleteById(id);
    }

    public Users saveUser(Users user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setCreating_date(LocalDateTime.now());
        Users savedUser = usersRepository.save(user);
        return savedUser;
    }
    
    

}
