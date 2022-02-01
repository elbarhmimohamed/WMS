package com.wms.controller;

import com.wms.model.emplacement.Emplacement;
import com.wms.repository.EmplacementRepository;
import com.wms.services.EmplacementServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class emplacementController {

    @Autowired
    private EmplacementServices emplacementServices;






    @GetMapping("/emplacement")
    private String getEmplacement(Model model){
        List<Emplacement> emplacements =new ArrayList<Emplacement>();
        emplacements =this.emplacementServices.getAllEmplacement();
        model.addAttribute("emplacements",emplacements);
        return "page/emplacement";

    }

    @GetMapping("/emplacement/configuration")
    private String configurationEmplacement(Model model){

        return "page/emplacement-configuration";

    }

}
