package com.wms.controller;

import com.wms.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Controller
public class DashboardController {
    @Autowired
    PersonServices personServices;
    @Autowired
    ComposantServices composantServices;
    @Autowired
    CategorieServices categorieServices;
    @Autowired
    InventaireServices inventaireServices;
    @Autowired
    CommandeServices commandeServices;
    @Autowired
    ReceptionServices receptionServices;

    @GetMapping("/admin/dashboard")
    public String dashboardAdminPage(Model model) {
        model.addAttribute("nombreClient", personServices.numberOfCustomer());
        model.addAttribute("nombreFournisseur", personServices.numberOfSupplier());
        model.addAttribute("nombreArticles", composantServices.numberOfArticle());
        model.addAttribute("nombreProduits", composantServices.numberOfProducte());
        model.addAttribute("nombreCat", categorieServices.getNumberOfCat());
        model.addAttribute("nombreInventaire", inventaireServices.numberOfInvantaire());
        model.addAttribute("nombreCommande", commandeServices.getNumberOfCommande());
        model.addAttribute("nombreReception", receptionServices.numberOfReception());
        LocalDateTime date = LocalDateTime.now();
        /*DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = dateFormatter.format(date1);*/
        model.addAttribute("date", date);
        return "/page/dashboard-admin";
    }
}
