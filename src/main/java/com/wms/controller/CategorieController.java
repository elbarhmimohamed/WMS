package com.wms.controller;

import com.wms.model.stock.Categorie;
import com.wms.model.stock.Composante;
import com.wms.services.CategorieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class CategorieController {
    @Autowired
    private CategorieServices categorieServices;


    @PostMapping("/CreateCategorie")
    public Categorie createCategorie(@RequestBody Categorie categorie) {
        return categorieServices.saveCategories(categorie);
    }

    @GetMapping("/categories/{id}")
    public Categorie getCategorie(@PathVariable("id") final Long id) {
        Optional<Categorie> cat = categorieServices.getCategorieById(id);
        if(cat.isPresent()) {
            return cat.get();
        } else {
            return null;
        }
    }

    @GetMapping("/categories")
    public Iterable<Categorie> getCategories() {
        return categorieServices.getAllCategories();
    }


    @PutMapping("/updatecategorie/{id}")
    public Categorie updateCategorie(@PathVariable("id") final Long id, @RequestBody Categorie categorie) {
        Optional<Categorie> e = categorieServices.getCategorieById(id);
        if(e.isPresent()) {
            Categorie currentCategorie = e.get();

            String name = categorie.getCategorie_name();
            if(name != null) {
                currentCategorie.setCategorie_name(name);
            }
            String description = categorie.getCategorie_description();
            if(description != null) {
                currentCategorie.setCategorie_description(description);;
            }

            List<Composante> composantes = categorie.getComposantes();
            if(composantes != null) {
                currentCategorie.setComposantes(composantes);
            }



            categorieServices.saveCategories(currentCategorie);
            return currentCategorie;
        } else {
            return null;
        }
    }



    @DeleteMapping("/deletecategorie/{id}")
    public void deleteCategorie(@PathVariable("id") final Long id) {
        categorieServices.deleteCategories(id);
    }
}
