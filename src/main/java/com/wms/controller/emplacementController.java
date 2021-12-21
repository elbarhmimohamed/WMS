package com.wms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class emplacementController {


    @GetMapping("/emplacement")
    private String getEmplacement(Model model){
        model.addAttribute("Hamza","helloworld");
        return "page/dashboard/administarateur/emplacement";

    }

}
