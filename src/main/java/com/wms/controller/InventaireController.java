package com.wms.controller;


import com.wms.model.operation.Inventaire;
import com.wms.model.personne.Users;
import com.wms.model.stock.Categorie;
import com.wms.model.stock.Composante;
import com.wms.model.stock.Inventaire_composante;
import com.wms.repository.InventaireRepository;
import com.wms.services.ComposantServices;
import com.wms.services.InventaireServices;
import com.wms.services.Inventaire_composanteServices;
import com.wms.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class InventaireController {

    @Autowired
    InventaireServices inventaireServices;

    @Autowired
    Inventaire_composanteServices inventaire_composanteServices;
    @Autowired
    ComposantServices composantServices;

    @Autowired
    InventaireRepository inventaireRepository;
    @Autowired
    UsersServices usersServices;

    @GetMapping("/inventaire")
    public String getInventaires(Model model) {
        Iterable<Inventaire> inventaires = inventaireServices.getAllInventaire();
        List<Composante> composantes = composantServices.getAllComposants();
        model.addAttribute("composantes",composantes);
        model.addAttribute("inventaires",inventaires);
        model.addAttribute("inventaire_id",new Inventaire());
        Inventaire newinventaire = new Inventaire();
        List<Inventaire_composante> invcomp = new ArrayList<>();
        for (int i = 0; i < composantes.size() ; i++) {
            Composante composante = composantes.get(i);
            Inventaire_composante inventaire_composante = new Inventaire_composante();
            inventaire_composante.setComposante(composante);
            inventaire_composante.setEcart(composante.getQuantity());
            invcomp.add(inventaire_composante);
        }
        newinventaire.setInventaire_composantes(invcomp);
        Users user = new Users(); user.setName("mohamed");
        newinventaire.setUser(user);
        model.addAttribute("newinventaire",newinventaire);
        return "/page/inventaire";
    }

    //------- ajouter

    @PostMapping("/ajouterinventaire")
    public String ajouterInventaire(Inventaire newinventaire , BindingResult result, Model model) {


        String username = newinventaire.getUser().getName();
        newinventaire.setUser(usersServices.getUserByName(username));
        //newinventaire.setDate(LocalDateTime.now());
        newinventaire.setReference("INV"+newinventaire.getReference());
        for (int i=0; i<newinventaire.getInventaire_composantes().size(); i++){
            String composantename = newinventaire.getInventaire_composantes().get(i).getComposante().getName();
            newinventaire.getInventaire_composantes().get(i).setComposante(composantServices.getComposanteByName(composantename).get());

        }
        inventaireServices.saveInventaire(newinventaire);
        return "redirect:/inventaire";


    }

    @GetMapping("/ligneinventaire")
    @ResponseBody
    public String ReceptionPage(@RequestParam int id) {

        String resultat = "";
        Inventaire inventaire = inventaireServices.getInventaireById(id);
        for (Inventaire_composante inventaire_composante:inventaire.getInventaire_composantes()) {
            resultat += "         <tr>\n" +
                    " <td >"+inventaire_composante.getComposante().getName()+" </td>\n" +
                    " <td >"+inventaire_composante.getComposante().getQuantity()+" </td>\n" +
                    " <td >"+inventaire_composante.getQuantityInReality()+"</td>\n" +
                    " <td >"+inventaire_composante.getEcart()+"</td>\n" +
                    "  </tr>";
        }
        return resultat;

    }
    // supprimer -->

    @PostMapping("/supprimerinventaire")
    public String supprimerInventaire(Inventaire inventaire_id, BindingResult result, Model model) {
        Inventaire inventaire  = inventaireServices.getInventaireById(inventaire_id.getId());

        inventaireServices.deleteById(inventaire_id.getId());
        return "redirect:/inventaire";
    }



}
