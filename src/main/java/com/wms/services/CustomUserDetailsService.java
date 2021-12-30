package com.wms.services;

import com.wms.model.personne.CustomUserDetails;
import com.wms.model.personne.Users;
import com.wms.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;





    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Users user = usersRepository.findByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }


}
