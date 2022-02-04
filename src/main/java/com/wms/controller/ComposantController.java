package com.wms.controller;

import com.wms.model.personne.Person;
import com.wms.model.stock.Categorie;
import com.wms.model.stock.Composante;
import com.wms.services.CategorieServices;
import com.wms.services.ComposantServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class ComposantController {

    @Autowired
    private ComposantServices composantServices;

    @Autowired
    private CategorieServices categorieServices;
    //--------- table of article

    @GetMapping("/article")
    public String articlePage(Model model) {
        Iterable<Composante> articles = composantServices.getAllComposants();
        Iterable<Categorie> categories = categorieServices.getAllCategories();
        model.addAttribute("articles",articles);
        model.addAttribute("categories",categories);
        model.addAttribute("composante", new Composante());
        model.addAttribute("composante_id", new Composante());
        return "/page/article";
    }






    //------------ modifier

        @PostMapping("/modifierarticle")
        public String modifierarticle(Composante composante ,BindingResult result, Model model) {

            Categorie cat = categorieServices.getCategorieByName(composante.getCategorie().getCategorie_name());
            composante.setCategorie(cat);

            composantServices.updateComposante(composante.getId(), composante);
            return "redirect:/article";

        }

//-------ajouter

    @PostMapping("/ajouterarticle")
    public String ajouterArticle(Composante newcomposante ,BindingResult result, Model model) {
        Categorie cat = categorieServices.getCategorieByName(newcomposante.getCategorie().getCategorie_name());
        newcomposante.setCategorie(cat);
        composantServices.saveComposante(newcomposante);
        return "redirect:/article";
    }
    @PostMapping("/ajouterproduit")
    public String ajouterProduit(Composante composante, BindingResult result, Model model) {
        Categorie cat = categorieServices.getCategorieByName(composante.getCategorie().getCategorie_name());
        composante.setCategorie(cat);
        composantServices.saveProduct(composante);
        return "redirect:/article";
    }
//--------------delete

    @PostMapping("/supprimerarticle")
    public String supprimerArticle(Composante composante_id, BindingResult result, Model model) {
        composantServices.deleteComposante(composante_id.getId());
        return "redirect:/article";
    }


}
