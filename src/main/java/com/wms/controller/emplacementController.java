package com.wms.controller;

import com.wms.model.emplacement.ConfigEmplacement;
import com.wms.model.emplacement.Emplacement;
import com.wms.model.operation.Palette;
import com.wms.model.personne.Users;
import com.wms.repository.ConfigEmplacementRepository;
import com.wms.repository.EmplacementRepository;
import com.wms.repository.PaletteRepository;
import com.wms.services.ConfigEmplacementServices;
import com.wms.services.EmplacementServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class emplacementController {

    @Autowired
    private EmplacementServices emplacementServices;
    @Autowired
    EmplacementRepository emplacementRepository;
    @Autowired
    private ConfigEmplacementRepository configEmplacementRepository;
    @Autowired
    private PaletteRepository paletteRepository;






    @GetMapping("/emplacement")
    private String getEmplacement(Model model){
        List<Emplacement> emplacements =new ArrayList<Emplacement>();
        emplacements =this.emplacementServices.getAllEmplacement();
        model.addAttribute("emplacements",emplacements);

        List<ConfigEmplacement> configEmplacement;
        configEmplacement =this.configEmplacementRepository.findAll();
        List<String> nbrrangee=this.configEmplacementRepository.findallrangee();
        model.addAttribute("rangee",nbrrangee);

        model.addAttribute("niveau",5);
        model.addAttribute("rack",5);
        model.addAttribute("position",3);
        model.addAttribute("configEmplacement",configEmplacement);
        model.addAttribute("emplacementRepository",emplacementRepository);
        return "page/emplacement";

    }

    @GetMapping("/emplacement/configuration")
    private String configurationEmplacement(Model model){
        List<ConfigEmplacement> configEmplacement;
        if(this.configEmplacementRepository.findAll().size()==0){
            configEmplacement =null;
        }else{
            configEmplacement=this.configEmplacementRepository.findAll();
        }

        model.addAttribute("configemplacements",configEmplacement);
        model.addAttribute("rangee", new ConfigEmplacement());
        model.addAttribute("id", new ConfigEmplacement());
        return "page/emplacement-configuration";
    }

    @PostMapping("/supprimerEmplacement")
    public String supprimerEmplacement(ConfigEmplacement configEmplacement, BindingResult result, Model model) {
            this.configEmplacementRepository.delete(configEmplacementRepository.findByIndexRangee(configEmplacement.getIndexRangee()));
        return "redirect:/emplacement/configuration";
    }

    @PostMapping("/ajouterrangee")
    public String ajouterRangee(ConfigEmplacement configEmplacement, BindingResult result, Model model) {
        this.configEmplacementRepository.save(configEmplacement);
        return "redirect:/emplacement/configuration";
    }

    @PostMapping("/supprimerToutRangee")
    public String supprimerToutRangee(HttpSession session, Model model) {
        this.configEmplacementRepository.deleteAll();
        return "redirect:/emplacement/configuration";
    }

    @PostMapping("/libérerToutRangee")
    public String libérerToutRangee(HttpSession session, Model model) {
        this.configEmplacementRepository.libérerToutRangee();
        return "redirect:/emplacement/configuration";
    }










    @PostMapping("/process_creating_emplacement")
    public String processCreatingEmplacement(Emplacement emplacement) {
        Emplacement emp = emplacementServices.getEmplacemetByRef(emplacement.getRefemplacement());
        //if (user1 == null || !user1.getEmail().equals(user.getEmail())) {
        if (emp == null ) {
            emplacementServices.saveEmplacement(emplacement);
            return " ";
        }
        return "/403";

    }


}
