package com.wms.controller;


import com.wms.model.operation.Commande;
import com.wms.model.personne.Person;
import com.wms.model.personne.Users;
import com.wms.model.stock.Composante;
import com.wms.repository.CommandeRepository;
import com.wms.repository.LigneCommandeRepository;
import com.wms.services.CommandeServices;
import com.wms.services.ComposantServices;
import com.wms.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class AppController {


    @Autowired
    CommandeServices commandeServices;
    @Autowired
    PersonServices personServices;
    @Autowired
    ComposantServices composantServices;
    @Autowired
    CommandeRepository commandeRepository;
    @Autowired
    LigneCommandeRepository ligneCommandeRepository;


    @GetMapping("/")
    public String viewHomePage() {
        return "page/index";
    }


    @GetMapping("/expedition")
    public String ExpeditionPage() {
        return "/page/Dashboard/agent/expedition";
    }
    @GetMapping("/gestionStock")
    public String GestionStockPage() {
        return "/page/Dashboard/operateur/gestionStock";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "/page/login";
    }

    @GetMapping("/journal")
    public String journalPage() {
        return "/page/journal";
    }

    @GetMapping("/admin/dashboard")
    public String dashboardAdminPage() {
        return "/page/dashboard-admin";
    }




    @GetMapping("/livraison")
    public String livraisonPage() {
        return "/page/livraison";
    }

    @GetMapping("/inventaire")
    public String inventairePage() {
        return "/page/inventaire";
    }

    @GetMapping("/utilisateur/motdepasse")
    public String ulilisateurPage() {
        return "/page/utlilisateurmotdepass";
    }

    @GetMapping("/403")
    public String view403Page() {
        return "page/403";
    }









}
