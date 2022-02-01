package com.wms.controller;


import com.wms.model.personne.Person;
import com.wms.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class PersonController {


    @Autowired
    private PersonServices personServices;


    //--------------- GetPersons -----------

    @GetMapping("/customer")
    public String getClients(Model model) {
        Iterable<Person> clients = personServices.getClients();
        model.addAttribute("person",clients);
        model.addAttribute("isCustomer", true);
        return "page/person/person";
    }
    @GetMapping("/fournisseur")
    public String getFournisseur(Model model) {
        Iterable<Person> fournisseurs = personServices.getFournisseurs();
        model.addAttribute("isCustomer", false);
        model.addAttribute("person",fournisseurs);
        return "page/person/person";
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
    @PostMapping("/savePerson")
    public Person createPerson(@RequestBody Person person) {
        if(person.isRole()){
            return personServices.saveCustomer(person);
        }

        return personServices.saveSupplier(person);
    }



    //---------------- update ------------------------

    @PutMapping("/Updateperosn/{id}")
    public Person updatePseron(@PathVariable("id") final Long id, @RequestBody Person person) {
        Optional<Person> e = personServices.getPerson(id);
        if(e.isPresent()) {
            personServices.updatePerson(id, person);
            return person;
            }
             else {
            return null;
        }
    }

    //--------------- Delete -------------

    @DeleteMapping("/deletePerson/{id}")
    public void deletePerson(@PathVariable("id") final Long id) {
        personServices.deletePerson(id);
    }


}
