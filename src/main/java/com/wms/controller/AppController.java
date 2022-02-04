package com.wms.controller;


import com.wms.model.personne.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class AppController {


    @GetMapping("/")
    public String viewHomePage() {
        return "page/index";
    }

    @GetMapping("/receptions")
    public String ReceptionPage() {
        return "/page/reception";
    }
    @GetMapping("/expedition")
    public String ExpeditionPage() {
        return "/page/Dashboard/agent/expedition";
    }
    @GetMapping("/gestionStock")
    public String GestionStockPage() {
        return "/page/Dashboard/operateur/gestionStock";
    }
/*
    @GetMapping("/utilisateur-config")
    public String utilisateurconfigPage() {
        return "/page/utilisateur-config";
    }
*/
    @GetMapping("/login")
    public String loginPage() {
        return "/page/login";
    }
    /*
    @GetMapping("/article")
    public String articlePage() {
        return "/page/article";
    }
*/
    @GetMapping("/journal")
    public String journalPage() {
        return "/page/journal";
    }

    @GetMapping("/admin/dashboard")
    public String dashboardAdminPage() {
        return "/page/dashboard-admin";
    }

    @GetMapping("/reception")
    public String receptionPage() {
        return "/page/reception";
    }

    @GetMapping("/commande")
    public String commandePage() {
        return "/page/commande";
    }

    @GetMapping("/livraison")
    public String livraisonPage() {
        return "/page/livraison";
    }

    @GetMapping("/inventaire")
    public String inventairePage() {
        return "/page/inventaire";
    }
/*
    @GetMapping("/clients")
    public String clientPage() {
        return "/page/client";
    }



    @GetMapping("/fournisseurs")
    public String fournisseurPage() {
        return "/page/fournisseur";
    }
*/
    @GetMapping("/utilisateur/motdepasse")
    public String ulilisateurPage() {
        return "/page/utlilisateurmotdepass";
    }

    @GetMapping("/403")
    public String view403Page() {
        return "page/403";
    }









}
