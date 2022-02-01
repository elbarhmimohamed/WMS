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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

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

        user.setCreating_date(LocalDateTime.now());
        Users user1 = usersRepository.findByName(user.getName());
        if (user1 == null ) {
            String bcryptPassword = BCryptPasswordEncod(user.getPassword());
            user.setPassword(bcryptPassword);
            return usersRepository.save(user);
        }
            return user;
    }

    public String BCryptPasswordEncod(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        return  encodedPassword;
    }

    public void updateUser(final Long id, Users user) {
        Optional<Users> e = usersRepository.findById( id);

        if(e.isPresent()) {
            String name = user.getName();
            String email = user.getEmail();
            String role = user.getRole();
            String password = user.getPassword();

            if(name != null || email != null || role != null || password != null ){
            if(name != null) {
                usersRepository.updateNameofUser(id,name);
            }
            if(email != null) {
                usersRepository.updateEmailofUser(id,email);
            }
            if(role != null) {
                usersRepository.updateRoleofUser(id,role);
            }
            if(password != null) {
                usersRepository.updateEmailofUser(id,BCryptPasswordEncod(password));
            }

                usersRepository.updateEditingDateofUser(id, LocalDateTime.now());

            }
            else{
                System.out.println( "aucunne modification !!! ");
            }

        } else {
            System.out.println( "Error de modification ");

        }

    }

    

}
