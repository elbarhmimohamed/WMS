package com.wms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class emplacementController {


    @GetMapping("/emplacement")
    private String getEmplacement(Model model){
        model.addAttribute("Hamza","helloworld");

        return "page/dashboard/administarateur/emplacement";

    }

}
