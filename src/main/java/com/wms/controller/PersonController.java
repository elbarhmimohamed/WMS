package com.wms.controller;


import com.wms.model.personne.Person;
import com.wms.model.stock.Categorie;
import com.wms.model.stock.Composante;
import com.wms.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.servlet.http.HttpSession;
import java.util.Optional;


@Controller
public class PersonController {


    @Autowired
    private PersonServices personServices;


    //--------------- GetPersons -----------

    @GetMapping("/Clients")
    public String getClients(Model model) {
        Iterable<Person> clients = personServices.getClients();
        model.addAttribute("clients",clients);
        model.addAttribute("person_id",new Person());
        model.addAttribute("newperson",new Person());
        model.addAttribute("person",new Person());
        return "/page/client";
    }
    @GetMapping("/fournisseurs")
    public String getFournisseur(Model model) {
        Iterable<Person> fournisseurs = personServices.getFournisseurs();
        model.addAttribute("fournisseurs",fournisseurs);
        model.addAttribute("person_id",new Person());
        model.addAttribute("newperson",new Person());
        model.addAttribute("person",new Person());
        return "/page/fournisseur";
    }


    @GetMapping("/person/{id}")
    public Person getPerson(@PathVariable("id") final Long id) {
        Optional<Person> person = personServices.getPerson(id);
        if(person.isPresent()) {
            return person.get();
        } else {
            return null;
        }
    }
    //---------------- Save ---------------------





    @PostMapping("/ajouterclient")
    public String ajouterClient(Person newperson ,BindingResult result, Model model) {
        personServices.saveCustomer(newperson);
        return "redirect:/Clients";

    }


    @PostMapping("/ajouterfournisseur")
    public String ajouterFournisseur(Person newperson ,BindingResult result, Model model) {
        personServices.saveSupplier(newperson);
        return "redirect:/fournisseurs";

    }



    //---------------- update ------------------------


    @PostMapping("/modifierperson")
    public String modifierclient(Person person ,BindingResult result, Model model ) {
        boolean role = personServices.getPersonById(person.getId()).isRole();
        personServices.updatePerson(person.getId(),person);

        if ( role ){
            return "redirect:/Clients";
        }
        return "redirect:/fournisseurs";

    }



    //--------------- Delete -------------


    @PostMapping("/supprimerperson")
    public String supprimerPerson(Person person_id, BindingResult result, Model model) {
        boolean role = personServices.getPersonById(person_id.getId()).isRole();
        personServices.deletePerson(person_id.getId());

        if (role){
            return "redirect:/Clients";
        }
        return "redirect:/fournisseurs";
    }




}
