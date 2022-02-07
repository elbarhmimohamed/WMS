 package com.wms.controller;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.wms.model.stock.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import org.springframework.web.servlet.ModelAndView;

@Getter
@Setter
@Data
@Controller
public class UsersController {



    @Autowired
    private UsersServices usersService;


    @GetMapping("/utilisateur-config")
    public String utilisateurconfigPage(Model model) {
        Iterable<Users> users = usersService.getUsers();
        List<String> roles = Arrays.asList("Opérateur","Agent Expédition/Réception","Admin");
        model.addAttribute("roles",roles);
        model.addAttribute("users",users);
        model.addAttribute("user_id",new Users());
        model.addAttribute("newuser",new Users());
        model.addAttribute("user",new Users());

        return "/page/utilisateur-config";
    }

	/*
	@GetMapping("/users/{id}")
	public Users getUser(@PathVariable("id") final Long id) {
		Optional<Users> user = usersService.getUser(id);
		if(user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}
	

	//------------------------Update -----------------------------
	@GetMapping("/updateUser/{id}")
	public String viewUpdateUserPage(@PathVariable("id") final Long id , Model model) {
		Users user = this.getUser(id);
		if(user != null ){
			List<String> listOfRole = Arrays.asList("Opérateur","Agent Expédition/Réception","Admin");
			model.addAttribute("listOfRole",listOfRole);
			model.addAttribute("user",user);
			return "page/login/updateUser";
		}
		return "page/403";

	}
	@PostMapping("/updateUserProcess/{id}")
	public String updateUserProcess( @PathVariable("id") final Long id, Users user, Model model) {
		Optional<Users> e = usersService.getUser(id);
		if(e.isPresent()) {
			usersService.updateUser(id,user);
			return this.getUsers(model);
		} else {
			return "page/403";
		}
	}
	
	//------------------delete------------------


	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable("id") final Long id , Model model) {

		usersService.deleteuser(id);
		model.addAttribute("message", "delete successful");
		return getUsers( model);
	}

	//-------------------------Register --------------------------//


	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		List<String> listOfRole = Arrays.asList("Opérateur","Agent Expédition/Réception","Admin");
		model.addAttribute("listOfRole",listOfRole);
		model.addAttribute("user", new Users());

		return "page/login/signup_form";
	}

	@PostMapping("/process_register")
	public String processRegister(Users user) {
		Users user1 = usersService.getUserByName(user.getName());
		//if (user1 == null || !user1.getEmail().equals(user.getEmail())) {
		if (user1 == null ) {
			usersService.saveUser(user);
			return "page/login/register_success";
		}
		return "/403";

	}

	//-------------------------Get users --------------------------//
	@GetMapping("/listofUsers")
	public String getUsers(Model model) {
		Iterable<Users> usres = usersService.getUsers();
		model.addAttribute("listUsers", usres);
		return "page/login/usersList";
	}
*/
    //------------ modifier

    @PostMapping("/modifierutilisateur")
    public String modifierCategorie(Users user ,BindingResult result, Model model) {

        usersService.updateUser(user.getId(), user);
        return "redirect:/utilisateur-config";

    }

    //------- ajouter

    @PostMapping("/ajouterutilisateur")
    public String ajouterUser(Users newuser ,BindingResult result, Model model) {
        usersService.saveUser(newuser);
        return "redirect:/utilisateur-config";
    }

    //--------------delete


    @PostMapping("/supprimerutilisateur")
    public String supprimerUser(Users user_id, BindingResult result, Model model) {
        usersService.deleteuser(user_id.getId());
        return "redirect:/utilisateur-config";
    }



}