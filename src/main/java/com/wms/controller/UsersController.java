 package com.wms.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wms.model.personne.Users;
import com.wms.services.UsersServices;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;

@Getter
@Setter
@Data
@RestController
public class UsersController {



    @Autowired
    private UsersServices usersService;
    
   
	
	/**
	 * Create - Add a new employee
	 * @return The employee object saved
	 */
	@PostMapping("/users")
	public Users createUSer(@RequestBody Users user) {
		return usersService.saveUser(user);
	}
	
	
	/**
	 * Read - Get one employee 
	 * @param id The id of the employee
	 * @return An Employee object full filled
	 */
	@GetMapping("/users/{id}")
	public Users getUser(@PathVariable("id") final Long id) {
		Optional<Users> user = usersService.getUser(id);
		if(user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}
	
	/**
	 * Read - Get all employees
	 * @return - An Iterable object of Employee full filled
	 */
	@GetMapping("/users")
	public Iterable<Users> getEmployees() {
		return usersService.getUsers();
	}
	
	/**
	 * Update - Update an existing employee
	 * @param id - The id of the employee to update
	 * @return
	 */
	@PutMapping("/users/{id}")
	public Users updateUser(@PathVariable("id") final Long id, @RequestBody Users user) {
		Optional<Users> e = usersService.getUser(id);
		if(e.isPresent()) {
			Users currentUser = e.get();
			
			
			String name = user.getName();
			if(name != null) {
				currentUser.setName(name);
			}
			
			String mail = user.getEmail();
			if(mail != null) {
				currentUser.setEmail(mail);
			}
			String password = user.getPassword();
			if(password != null) {
				currentUser.setPassword(password);;
			}
			usersService.saveUser(currentUser);
			return currentUser;
		} else {
			return null;
		}
	}
	
	
	/**
	 * Delete - Delete an employee
	 * @param id - The id of the employee to delete
	 */
	@DeleteMapping("/users/{id}")
	public void deleteEmployee(@PathVariable("id") final Long id) {
		usersService.deleteuser(id);
	}
	

    
}