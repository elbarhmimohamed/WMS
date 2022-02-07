package com.wms.controller;

import com.wms.model.personne.Person;
import com.wms.model.stock.Categorie;
import com.wms.model.stock.Composante;
import com.wms.services.CategorieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class CategorieController {
    @Autowired
    private CategorieServices categorieServices;


    @GetMapping("/categories")
    public String getCategories(Model model) {
        Iterable<Categorie> categories = categorieServices.getAllCategories();
        model.addAttribute("categories",categories);
        model.addAttribute("categorie_id",new Categorie());
        model.addAttribute("newcategorie",new Categorie());
        model.addAttribute("categorie",new Categorie());
        return "/page/categorie";
    }


    //------- ajouter

    @PostMapping("/ajoutercategorie")
    public String ajouterCategorie(Categorie newcategorie ,BindingResult result, Model model) {
        categorieServices.saveCategories(newcategorie);
        return "redirect:/categories";
    }



    @GetMapping("/categories/{id}")
    public Categorie getCategorie(@PathVariable("id") final Long id) {
        Categorie cat = categorieServices.getCategorieById(id);
        if(cat != null) {
            return cat;
        } else {
            return null;
        }
    }


    //------------ modifier

    @PostMapping("/modifiercategorie")
    public String modifierCategorie(Categorie categorie ,BindingResult result, Model model) {

        categorieServices.updateCategories(categorie.getId(), categorie);
        return "redirect:/categories";

    }




    //--------------delete


    @PostMapping("/supprimercategorie")
    public String supprimerCategorie(Categorie categorie_id, BindingResult result, Model model) {
        categorieServices.deleteCategories(categorie_id.getId());
        return "redirect:/categories";
    }

}
