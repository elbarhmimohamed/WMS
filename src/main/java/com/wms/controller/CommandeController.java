package com.wms.controller;

import com.wms.model.emplacement.ConfigEmplacement;
import com.wms.model.operation.Commande;
import com.wms.model.operation.FichierStock;
import com.wms.model.operation.LigneCommande;
import com.wms.model.personne.Person;
import com.wms.model.stock.Composante;
import com.wms.repository.CommandeRepository;
import com.wms.repository.LigneCommandeRepository;
import com.wms.services.CommandeServices;
import com.wms.services.ComposantServices;
import com.wms.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommandeController {

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

    @GetMapping("/commande")
    public String index(Model model) {
        List<Commande> commandeList = commandeServices.findAllCommande();
        if(commandeList.size() ==0) commandeList =null;
        List <Person> fournisseurList = personServices.getFournisseurs();
        if(fournisseurList.size() ==0) commandeList =null;
        List <Composante> composanteList = composantServices.getAllComposants();
        if(composanteList.size() ==0) commandeList =null;
        Commande commandeAdd = new Commande();


        model.addAttribute("commande",commandeList);
        model.addAttribute("fournisseur",fournisseurList);
        model.addAttribute("commandeAdd",commandeAdd);
        model.addAttribute("composants",composanteList);
        model.addAttribute("commanderemove",new Commande());

        return "/page/commande";

    }

    @PostMapping("/ajouterCommande")
    public String ajouterCommande(Commande commande, HttpServletRequest request) {

        String [] idarticle = request.getParameterValues("idarticle");
        String [] prix = request.getParameterValues("prix");
        String [] quntite = request.getParameterValues("quantite");
        List<LigneCommande> ligneCommande = new ArrayList<>();

        for(int i = 0 ; i< idarticle.length;i++){
            if(idarticle[i]!="Article"){
                LigneCommande ligneadd = new LigneCommande();
                ligneadd.setCommande(commande);
                ligneadd.setQuantite(Integer.parseInt(quntite[i]));
                ligneadd.setComposante(composantServices.getComposanteById(Long.parseLong(idarticle[i])));
                ligneadd.setPrix(Double.parseDouble(prix[i]));
                ligneCommande.add(ligneadd);
                ligneCommandeRepository.save(ligneadd);
            }
        }
        commande.setLigneCommande(ligneCommande);
        commandeRepository.save(commande);
        return "redirect:/commande";
    }



    @PostMapping("/supprimerCommande")
    public String commanderemove(Commande commande, BindingResult result, Model model) {
        this.commandeRepository.deleteById(commande.getId());
        return "redirect:/stockage";
    }






}
