package com.wms.services;



import java.time.LocalDateTime;
import java.util.Optional;

import com.wms.model.personne.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public Users getUserByName(final String name) {
        Users user = usersRepository.findByName(name);
        if (user == null) {
            return  null ;
        }
        return  user;
    }


    public Iterable<Users> getUsers() {
        return usersRepository.findAll();
    }

    public void deleteuser(final Long id) {
        usersRepository.deleteById(id);
    }

    public Users saveUser(Users user) {
        BCryptPasswordEncod(user);
        user.setCreating_date(LocalDateTime.now());
        Users user1 = usersRepository.findByName(user.getName());
        if (user1 == null || !user1.getEmail().equals(user.getEmail())) {
            return usersRepository.save(user);
        }
        return user;
    }





    public Users BCryptPasswordEncod(Users user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return  user;
    }
    

}
