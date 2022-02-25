package com.wms.controller;

import com.wms.model.operation.Inventaire;
import com.wms.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
        LocalDate currentLocalDate = LocalDate.now();
        model.addAttribute("date", currentLocalDate);
        Inventaire di = this.getLastInventaire();
        model.addAttribute("inv", di);
        model.addAttribute("qte", getAllqte(di));
        return "/page/dashboard-admin";
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("nombreClient", personServices.numberOfCustomer());
        model.addAttribute("nombreFournisseur", personServices.numberOfSupplier());
        model.addAttribute("nombreArticles", composantServices.numberOfArticle());
        model.addAttribute("nombreProduits", composantServices.numberOfProducte());
        model.addAttribute("nombreCat", categorieServices.getNumberOfCat());
        model.addAttribute("nombreInventaire", inventaireServices.numberOfInvantaire());
        model.addAttribute("nombreCommande", commandeServices.getNumberOfCommande());
        model.addAttribute("nombreReception", receptionServices.numberOfReception());
        LocalDate currentLocalDate = LocalDate.now();

        model.addAttribute("date", currentLocalDate);
        Inventaire di = this.getLastInventaire();
        model.addAttribute("inv", di);
        model.addAttribute("qte", getAllqte(di));
        return "/page/dashboard-admin";
    }

    Inventaire getLastInventaire(){
        List<Inventaire> listinv = inventaireServices.getAllInventaire();
        if(!listinv.isEmpty()){
            Inventaire di = listinv.get(0);
            for (int i = 0; i < listinv.size(); i++) {
                if (di.getId() < listinv.get(i).getId()) {
                    di = listinv.get(i);
                }
            }
            return di;
        }
        return null;
    }

    double getAllqte(Inventaire inv ){
        if (inv != null) {
            double x = 0;
            for (int i = 0; i < inv.getInventaire_composantes().size(); i++) {
                x = x + inv.getInventaire_composantes().get(i).getComposante().getQuantity();
            }
            return x;
        }
        else {
            return 0;
        }
    }
}
