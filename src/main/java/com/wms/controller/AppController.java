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

    @GetMapping("/reception")
    public String ReceptionPage() {
        return "/page/Dashboard/agent/reception";
    }
    @GetMapping("/expedition")
    public String ExpeditionPage() {
        return "/page/Dashboard/agent/expedition";
    }
    @GetMapping("/gestionStock")
    public String GestionStockPage() {
        return "/page/Dashboard/operateur/gestionStock";
    }
    @GetMapping("/inventaire")
    public String InventairePage() {
        return "/page/Dashboard/operateur/inventaire";
    }

    @GetMapping("/login")
    public String viewLoginPage() {
        return "page/login/login";
    }

    @GetMapping("/403")
    public String view403Page() {
        return "page/403";
    }









}
