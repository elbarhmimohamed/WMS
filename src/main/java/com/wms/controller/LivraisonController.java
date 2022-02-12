package com.wms.controller;

import com.lowagie.text.DocumentException;
import com.wms.model.operation.*;
import com.wms.model.personne.Person;
import com.wms.model.stock.Composante;
import com.wms.repository.*;
import com.wms.services.CommandeServices;
import com.wms.services.ComposantServices;
import com.wms.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LivraisonController {

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
    @Autowired
    ReceptionRepository receptionRepository;
    @Autowired
    ComposantRepository composantRepository;
    @Autowired
    EmplacementRepository emplacementRepository;
    @Autowired
    LignelivraisonRepository lignelivraisonRepository;
    @Autowired
    PreparationCommandeRepository preparationCommandeRepository;
    @Autowired
    LivraisonRepository livraisonRepository;
    @Autowired
    TransportRepository transportRepository;

    @GetMapping("/preparationCommande")
    public String preparationCommande(Model model) {
        model.addAttribute("commandePremove", new PreparationCommande());

        List<PreparationCommande> preparationCommandes = preparationCommandeRepository.findAll();
        if (preparationCommandes.size() == 0) preparationCommandes = null;
        List<Person> clients = (List<Person>) personServices.getClients();
        if (clients.size() == 0) clients = null;
        List<Composante> composantes = composantServices.getAllComposants();
        if (composantes.size() == 0) composantes = null;

        model.addAttribute("preparationCommandes", preparationCommandes);
        model.addAttribute("clients", clients);
        model.addAttribute("preparationCommandeAdd", new PreparationCommande());
        model.addAttribute("composants", composantes);
        model.addAttribute("emplacementRepository", emplacementRepository);

        return "/page/preparationCommande";
    }

    @GetMapping("/livraison")
    public String livraison(Model model) {
        List<Livraison> livraisonList = livraisonRepository.findAll();
        if(livraisonList.size() ==0) livraisonList =null;

        model.addAttribute("livraisonList",livraisonList);
        model.addAttribute("livraison",new Livraison());
        model.addAttribute("preparationCommande",preparationCommandeRepository.findAll());
        model.addAttribute("livraisonRemove", new Livraison());
        model.addAttribute("livpdf",new Livraison());
        model.addAttribute("transportlist",transportRepository.findAll());

        return "/page/livraison";
    }


    @GetMapping("/lignedecommande")
    @ResponseBody
    public String lignecommmande(@RequestParam int na) {

       String resultat="" ;
        List<Composante> composantes =composantRepository.findAll();

        for(int i=1;i<na;i++){

            resultat += "         <tr>\n" + " <td><select name='articleid' id='aya' class='form-control form-control-lg'>\n" +
                    "                                                        <option value=''>--Veuillez choisir une article--</option>\n" +
                    "\n" ;

            for (Composante composante: composantes
                 ) {
                resultat += "<option data-qd='"+composante.getQuantity()+"\"' value='"+composante.getId()+"'>"+composante.getName()+"</option>\n" ;
            }

            resultat+="</select></td>" +
                    "<td id='z"+i+"'></td>" +
                    "<td><input type='number' class='form-control' name='qd'></td>" +
                    "<td></td></tr>";

        }



        return resultat;
    }

    @PostMapping("/ajouterpreparationcommmande")
    public String ajouterpreparatioCommande(PreparationCommande preparationCommande, HttpServletRequest request) {

        String [] idarticle = request.getParameterValues("idarticle");
        String [] emplacement = request.getParameterValues("emplacement");
        String [] quantite = request.getParameterValues("quantite");
        String [] prix = request.getParameterValues("prix");

        List<Lignelivraison> lignelivraisons = new ArrayList<>();

        for(int i = 0 ; i< idarticle.length;i++){
            if(prix[i]!=""){
                Lignelivraison ligneadd = new Lignelivraison();
                ligneadd.setPreparationCommande(preparationCommande);
                ligneadd.setComposante(composantServices.getComposanteById(Long.parseLong(idarticle[i])));
                ligneadd.setEmplacement(emplacementRepository.getById(Long.parseLong(emplacement[i])));
                ligneadd.setPrix(Double.parseDouble(prix[i]));
                ligneadd.setQuantite(Integer.parseInt(quantite[i]));
                lignelivraisons.add(ligneadd);

                lignelivraisonRepository.save(ligneadd);
                ligneadd.getComposante().setQuantity(ligneadd.getComposante().getQuantity()-ligneadd.getQuantite());

            }
        }
        preparationCommande.setLignelivraisons(lignelivraisons);
        preparationCommandeRepository.save(preparationCommande);
        return "redirect:/preparationCommande";
    }


    @PostMapping("/supprimerPCommande")
    public String commanderemove(PreparationCommande preparationCommande, BindingResult result, Model model) {
        this.preparationCommandeRepository.deleteById(preparationCommande.getId());
        return "redirect:/preparationCommande";
    }


    @PostMapping("/ajouterLivraison")
    public String ajouterCommande(Livraison livraison, HttpServletRequest request) {

      this.livraisonRepository.save(livraison);
        return "redirect:/livraison";
    }

    @PostMapping("/supprimerlivraison")
    public String supprimerlivraison(Livraison livraison, BindingResult result, Model model) {
        this.livraisonRepository.deleteById(livraison.getId());
        return "redirect:/livraison";
    }


    @GetMapping("/lignedepreparationdecommande")
    @ResponseBody
    public String peparationCommandeDetail(@RequestParam int id) {
        System.out.println("yyyyyyyyyyyyy");
        System.out.println("yyyyyyyyyyyyy");
        String resultat = "";
        PreparationCommande pc = preparationCommandeRepository.getById(id);

        for (Lignelivraison ll : pc.getLignelivraisons()) {
            resultat += "         <tr>\n" +
                    " <td >"+ll.getComposante().getName()+" </td>\n" +
                    " <td >"+ll.getComposante().getQuantity()+" </td>\n" +
                    " <td >"+ll.getQuantite()+" </td>\n" +
                    " <td >"+ll.getPrix()+"</td>\n" +
                    " <td >"+ll.getEmplacement().getRefemplacement()+"</td>\n" +
                    "  </tr>";
        }
        System.out.println("yyyyyyyyyyyyy");
        System.out.println("yyyyyyyyyyyyy");
        return resultat;

    }

    @GetMapping("/detaillivraison")
    @ResponseBody
    public String livraisonDetail(@RequestParam int id) {

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Livraison ll = livraisonRepository.getById(id);
        String datelivraison = dateFormatter.format(ll.getDate());
        String resultat = "";
        resultat = resultat +
                "<div class='col-md-12' >\n"+
                "<div class='card' > \n"+
                " <h4>Référence de réception : BL"+ ll.getReference() + "</h4> \n"+
                " <h4>Référence de Commande : PC"+ ll.getPreparationCommande().getReference() + " </h4> \n" +
                " <h4>Date de livraison     : "+ datelivraison +"</h4>\n" +
                " <h4>Date de préparation de commande     : "+ dateFormatter.format(ll.getPreparationCommande().getDate()) +"</h4>\n" +
                " <h4>Transport     : T"+ ll.getTransport().getReference() +"</h4>\n" +
                "</div> \n"+
                "</div> \n"+
                " <div class='col-md-12 '> \n"+
                " <div class='card'> <div class='card-body'> <div class='card-title'>\n"+
                " Commande </div>\n"+
                "<div class='table-responsive'> <table class='table  table-bordered'>\n"+
                "<thead> <tr>  \n" +
                "<th>Article</th>\n"+
                "<th>Quantité disponible</th>"+
                "<th>Quantité demandée </th>"+
                " <th>Prix</th>\n"+
                "<th>Emplacement</th>\n"+
                "</tr> </thead>\n"+
                "<tbody >  \n";

        ;
        // resultat = resultat + "<td colspan='2'> Commande "+ reception.getCommande().getReference()+"</td>\n";

        for (Lignelivraison llv :ll.getPreparationCommande().getLignelivraisons()) {
            resultat += "         <tr>\n" +
                    " <td >"+llv.getComposante().getName()+" </td>\n" +
                    " <td >"+llv.getComposante().getQuantity()+" </td>\n" +
                    " <td >"+llv.getQuantite()+" </td>\n" +
                    " <td >"+llv.getPrix()+" </td>\n" +
                    " <td >"+llv.getEmplacement().getRefemplacement()+" </td>\n" +
                    "  </tr>";
        };
        resultat = resultat + "</tbody> </table> </div> </div> </div> </div>\n";




        return resultat;

    }

    @PostMapping("/livraisonPdf")
    public void exportToPDF(Livraison livpdf, HttpServletResponse response) throws DocumentException, IOException {
        Livraison livraison = livraisonRepository.getById(livpdf.getId());
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = dateFormatter.format(livraison.getDate());
        String livraisonDate = dateFormatter.format(livraison.getDate());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Livraison_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        LivraisonPDFGenerateur livraisonPDFGenerateur = new LivraisonPDFGenerateur();
        livraisonPDFGenerateur.setRef(livraison.getReference());
        livraisonPDFGenerateur.setDate(livraisonDate);
        livraisonPDFGenerateur.setDescreption(livraison.getDescription());
        livraisonPDFGenerateur.setTransport(livraison.getTransport());
        livraisonPDFGenerateur.setPreparationCommande(livraison.getPreparationCommande());

        livraisonPDFGenerateur.export(response);


    }








}