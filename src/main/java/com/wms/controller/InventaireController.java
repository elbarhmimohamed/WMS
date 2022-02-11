package com.wms.controller;


import com.lowagie.text.DocumentException;
import com.wms.model.operation.Inventaire;
import com.wms.model.operation.InventairePDFGenerator;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
        model.addAttribute("invpdf",new Inventaire());
        Inventaire newinventaire = new Inventaire();
        Inventaire inventaire = new Inventaire();
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
        model.addAttribute("inventaire",inventaire);
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
    //-- modifier

    @GetMapping("/affichemodifierform")
    @ResponseBody
    public String Modifiermodal(@RequestParam int id, Model model) {

        String resultat = "";
        Inventaire inventaire = inventaireServices.getInventaireById(id);
        Inventaire inv = new Inventaire();
        List<Inventaire_composante> invC = new ArrayList<>();
        invC = inventaire.getInventaire_composantes();
        for (int i = 0; i < invC.size() ; i++) {
            Composante composante = invC.get(i).getComposante();
            Inventaire_composante inventaire_composante = new Inventaire_composante();
            inventaire_composante.setComposante(composante);
            inventaire_composante.setEcart(composante.getQuantity());
            invC.add(inventaire_composante);
        }
        inv.setInventaire_composantes(invC);
        model.addAttribute("inv",inv);
        resultat = resultat +
                " <div class='col-md-4 grid-margin'><div class='card' >"+
                " <div class='form-group'>\n"+
                "<label for='user2'>Utilisateur</label> \n"+
                "<input id='user2' th:field='*{user.name}'  readonly='readonly' class='form-control' value='"+inventaire.getUser().getName()+"'/>\n"+
                "</div><div class='form-group'>\n" +
                "<label>Référence</label> <div class='input-group'>\n"+
                "<div class='input-group-prepend'>\n"+
                "<span class='input-group-text'>INV-</span>'\n"+
                "</div><input th:field='*{reference}' type='text' class='form-control' value='"+inventaire.getReference()+"' placeholder='1111' aria-label='Username'>\n"+
                "</div></div><div class='form-group'><label for='exampleInputdate12'>date de l'inventaire</label>\n"+
                "<input type='date' th:field='*{date}' class='form-control' value='"+inventaire.getDate()+"'  placeholder='dd/mm/yyyy' id='exampleInputdate12' name='date' >\n"+
                "</div> </div></div></div> \n"+
                "<div class='col-md-8 grid-margin'><div class='card'><div class='card-body'> <p class='card-title mb-0'>Article </p><br>\n"+
                 "<div class='table-responsive'> <table class='table' id='table_id2'> \n"+
                "<thead><tr ><th>Article</th><th>Quantité du systéme</th><th>Quantité en stock</th></tr></thead><tbody> \n";

        /*
            resultat = resultat +

            " <tr th:each='inventaire_composante ,itemStat : *{ inventaire_composantes} '> \n" +
            " <td> <input  id='composante_name' th:field='*{inventaire_composantes[__${itemStat.index}__].composante.name}' th:value='${inventaire_composante.composante.name}'readonly='readonly' class='form-control'/></td>\n " +
                "<td> <input id='inputqte' th:value='${inventaire_composante.composante.quantity}'  readonly='readonly' class='form-control' /></td> \n"+
                "<td > <input id='inputqtestock' th:field='*{inventaire_composantes[__${itemStat.index}__].quantityInReality}' type='number'  class='form-control'  ></td> \n"+
                "</tr> \n";
        */



        resultat = resultat + "</tbody></table></table></div></div>\n";


        return resultat;

    }


    // supprimer -->

    @PostMapping("/supprimerinventaire")
    public String supprimerInventaire(Inventaire inventaire_id, BindingResult result, Model model) {
        inventaireServices.deleteById(inventaire_id.getId());
        return "redirect:/inventaire";
    }

    @PostMapping("/inventarePdf")
    public void exportToPDF(Inventaire invpdf,HttpServletResponse response) throws DocumentException, IOException {
        Inventaire inv = inventaireServices.getInventaireById(invpdf.getId());
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = dateFormatter.format(inv.getDate());
        String inventaireDate = dateFormatter.format(inv.getDate());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Inventaire_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);




        InventairePDFGenerator inventairePDFGenerator = new InventairePDFGenerator();
        inventairePDFGenerator.setUser(inv.getUser());
        inventairePDFGenerator.setDate(inventaireDate);
        inventairePDFGenerator.setListInvcomp(inv.getInventaire_composantes());
        inventairePDFGenerator.setRef(inv.getReference());

        inventairePDFGenerator.export(response);


    }





}
