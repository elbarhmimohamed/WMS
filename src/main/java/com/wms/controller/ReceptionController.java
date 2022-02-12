package com.wms.controller;

import com.lowagie.text.DocumentException;
import com.wms.model.emplacement.ConfigEmplacement;
import com.wms.model.emplacement.Emplacement;
import com.wms.model.operation.*;
import com.wms.repository.*;
import com.wms.services.CommandeServices;
import com.wms.services.ReceptionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReceptionController {
    @Autowired
    CommandeServices commandeServices ;
    @Autowired
    CommandeRepository commandeRepository ;
    @Autowired
    ReceptionRepository receptionRepository;
    @Autowired
    LigneCommandeRepository ligneCommandeRepository;
    @Autowired
    ControleQualiteRepository controleQualiteRepository;
    @Autowired
    ConfigEmplacementRepository configEmplacementRepository;
    @Autowired
    ComposantRepository composantRepository;
    @Autowired
    FichierStockRepository fichierStockRepository;
    @Autowired
    PaletteRepository paletteRepository;

    @GetMapping("/reception")
    public String ReceptionPage(Model model) {
        List<Commande> commandeList = commandeServices.findAllCommande();
        if(commandeList.size() ==0) commandeList =null;
        List<Reception> receptionList = receptionRepository.findAll();
        if(receptionList.size() ==0) receptionList =null;
        Reception receptionAdd = new Reception();
        model.addAttribute("commandeList",commandeList);
        model.addAttribute("reception",receptionList);
        model.addAttribute("receptionAdd",receptionAdd);
        model.addAttribute("receptionremove", new Reception());
        model.addAttribute("recpdf",new Reception());
        return "/page/reception";
    }

    @PostMapping("/ajouterReception")
    public String ajouterReception(Reception reception, HttpServletRequest request) {

        String [] quantitereel = request.getParameterValues("quantitereel");
        String [] echantillon = request.getParameterValues("echantillon");
        String [] defectueux = request.getParameterValues("defectueux");
        String [] idoofcommande = request.getParameterValues("idoofcommande");

        List<ControleQualite> controleQualites = new ArrayList<>();

        for(int i = 0 ; i< quantitereel.length;i++){
                 ControleQualite ligneadd = new ControleQualite();
                ligneadd.setReception(reception);
                ligneadd.setQuantiteReel(Integer.parseInt(quantitereel[i]));
                ligneadd.setEchantillon(Integer.parseInt(echantillon[i]));
                ligneadd.setDefectueux(Integer.parseInt(defectueux[i]));
                ligneadd.setLigneCommande(ligneCommandeRepository.getById(Integer.parseInt(idoofcommande[i])));
            if(request.getParameter(String.valueOf(ligneCommandeRepository.getById(Integer.parseInt(idoofcommande[i])).getId()))=="1"){
                ligneadd.setAccepter(true);
            }else{
                ligneadd.setAccepter(true);
            }


                 controleQualites.add(ligneadd);

        }

        reception.setControleQualiteList(controleQualites);
        receptionRepository.save(reception);
        return "redirect:/reception";
    }





    @GetMapping("/lignecommande")
    @ResponseBody
    public String lignecommmande(@RequestParam int id) {

        String resultat="" ;
        Commande commande =commandeRepository.getById(id);
        for (LigneCommande ligne:commande.getLigneCommande()) {

            resultat += "         <tr>\n" +
                    "                                                                       <input type='hidden'  name='idoofcommande'  value='"+ligne.getId()+"'>\n" +
                    "                                                                        <td><input type='string'  name='article' value='"+ligne.getComposante().getName()+"'> </td>\n" +
                    "                                                                        <td><input type='string' name='prix' value='"+String.valueOf(ligne.getPrix())+"'></td>\n" +
                    "                                                                        <td><input type='string' name='quantite' value='"+String.valueOf(ligne.getQuantite())+"'></td>\n" +
                    "                                                                        <td><input type='string' name='quantitereel'  ></td>\n" +
                    "                                                                        <td><input type='string' name='echantillon'></td>\n" +
                    "                                                                        <td><input type='string' name='defectueux'></td>\n" +
                    "<td><input type='radio' name='"+String.valueOf(ligne.getId())+"' id='GFG' value='1' checked>Accepter<br>" +
                    "<input type='radio' name='"+String.valueOf(ligne.getId())+"' id='GFG' value='2'>Refuser<br> </td>" ;

        }
        return resultat;
    }

    @PostMapping("/supprimerReception")
    public String supprimerReception(Reception reception, BindingResult result, Model model) {
        Reception reception1 = receptionRepository.getById(reception.getId());

        if(reception1.getFichierStock() != null){
            fichierStockRepository.deleteById(reception.getFichierStock().getId());
        }
        this.receptionRepository.deleteById(reception.getId());
        return "redirect:/reception";
    }

    @GetMapping("/stockage")
    public String StockagePage(Model model) {
        List<Reception> receptionList = receptionRepository.findAll();
        if(receptionList.size() ==0) receptionList =null;
        FichierStock fichierStock = new FichierStock();
        model.addAttribute("fichierStockList",fichierStockRepository.findAll());
        model.addAttribute("receptionList",receptionList);
        model.addAttribute("fichierStock",fichierStock);
        model.addAttribute("fichierStockRemove", new FichierStock());


        return "/page/stockage";
    }

    @GetMapping("/lignepalette")
    @ResponseBody
    public String lignepalette(@RequestParam int id,@RequestParam int nb) {

        String resultat="" ;
        Reception reception = receptionRepository.getById(id);

int k =0;
        for(int i=1;i<=nb;i++){


            resultat+="<tr>\n" +
                    "                                                                <td>Palette "+i+"</td>\n" +
                    "                                                                        <td><input type='string' name='reff'></td>\n" +
                    "                                                                <td><select name='articleid' class='form-control form-control-lg'>\n";

            for (ControleQualite controleQualite:reception.getControleQualiteList() ) {
                resultat+=          "                                                                        <option value='"+controleQualite.getLigneCommande().getId()+"'>"+ controleQualite.getLigneCommande().getComposante().getName()+ "</option>\n" ;

            }


resultat +=

                    "                                                                </select> <td><label>Rangee</label> <select name='rangee' class='form-control form-control-lg'>\n" +
                    "                                                                    <label>range</label>\n" ;
            for (ConfigEmplacement configEmplacement:configEmplacementRepository.findAll() ) {
                resultat+=          "                                                                        <option value='"+configEmplacement.getIndexRangee()+"'>"+ configEmplacement.getIndexRangee()+ "</option>\n" ;

            }


            resultat+=

                    "                                                                    </select>\n" +
                    "                                                                    <label>niveau</label>\n" +
                    "                                                                    <select class='form-control form-control-lg' id='numNiveau' name='numNiveau'>\n" +
                    "                                                                        <option value='1'>1</option>\n" +
                    "                                                                        <option value='2'>2</option>\n" +
                    "                                                                        <option value='3'>3</option>\n" +
                    "                                                                        <option value='4'>4</option>\n" +
                    "                                                                        <option value='5'>5</option>\n" +
                    "                                                                    </select>\n" +
                    "\n" +
                    "                                                                    <label>racke</label>\n" +
                    "                                                                    <select class='form-control form-control-lg' id='numRack' name='numRack'>\n" +
                    "                                                                        <option value='1'>1</option>\n" +
                    "                                                                        <option value='2'>2</option>\n" +
                    "                                                                        <option value='3'>3</option>\n" +
                    "                                                                        <option value='4'>4</option>\n" +
                    "                                                                        <option value='5'>5</option>\n" +
                    "                                                                    </select>\n" +
                    "\n" +
                    "                                                                    <label>position</label>\n" +
                    "                                                                    <select class='form-control form-control-lg' id='numPosition' name='numPosition'>\n" +
                    "                                                                        <option value='1'>1</option>\n" +
                    "                                                                        <option value='2'>2</option>\n" +
                    "                                                                        <option value='3'>3</option>\n" +
                    "                                                                        <option value='4'>4</option>\n" +
                    "                                                                        <option value='5'>5</option>\n" +
                    "                                                                    </select>\n" +
                    "\n" +
                    "                                                                </td>\n" +
                    "                                                            </tr>";

        }

        return resultat;
    }





    @PostMapping("/ajouterFichierStock")
    public String ajouterfichierStock(FichierStock fichierStock, HttpServletRequest request) {

        String [] idarticle = request.getParameterValues("articleid");
        String [] reff = request.getParameterValues("reff");
        String [] rangee = request.getParameterValues("rangee");
        String [] numNiveau = request.getParameterValues("numNiveau");
        String [] numRack = request.getParameterValues("numRack");
        String [] numPosition = request.getParameterValues("numPosition");

        List<Palette> palettes = new ArrayList<>();

        for(int i = 0 ; i< rangee.length;i++){
           Palette palette = new Palette();

           palette.setComposante(ligneCommandeRepository.getById(Integer.parseInt(idarticle[i])).getComposante());
           palette.setQuantite(ligneCommandeRepository.getById(Integer.parseInt(idarticle[i])).getComposante().getQuantity());
            Emplacement emplacement =new Emplacement();
           palette.setFichierStock(fichierStock);
            emplacement.setRefemplacement(rangee[i]+numNiveau[i]+"-R"+numRack[i]+"-p"+numPosition[i]);
            palette.setReference(reff[i]);
            emplacement.setTauxOccupation(100);
            emplacement.setPalette(palette);
           palette.setEmplacement(emplacement);
            palettes.add(palette);
            paletteRepository.save(palette);


        }
        fichierStock.setPalettes(palettes);
        fichierStockRepository.save(fichierStock);
        return "redirect:/stockage";
    }

    @PostMapping("/supprimerfichierStock")
    public String supprimerfichierStock(FichierStock fichierStock, BindingResult result, Model model) {
        this.fichierStockRepository.deleteById(fichierStock.getId());
        return "redirect:/stockage";
    }

    @Autowired
    ReceptionServices receptionServices;
    //**** details
    @GetMapping("/detailreception")
    @ResponseBody
    public String ReceptionDetail(@RequestParam int id) {
        System.out.println(id);
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Reception reception = receptionServices.findReceptionById(id);
        String datereception = dateFormatter.format(reception.getDate());
        String resultat = "";
        resultat = resultat +
                "<div class='col-md-12' >\n"+
                "<div class='card' > \n"+
               " <h4>Référence de réception : BC"+ reception.getReference() + "</h4> \n"+
                " <h4>Référence de Commande : BR"+ reception.getCommande().getReference() + " </h4> \n" +
                " <h4>Date de reception     : "+ datereception +"</h4>\n" +
                "</div> \n"+
                "</div> \n"+
                " <div class='col-md-12 '> \n"+
                " <div class='card'> <div class='card-body'> <div class='card-title'>\n"+
                " Controle qualité </div>\n"+
                "<div class='table-responsive'> <table class='table  table-bordered'>\n"+
                "<thead> <tr>  \n" +
                "<th>Article</th>\n"+
                  " <th>Prix</th>\n"+
                    "<th>Quantité demandée </th>"+
                "<th>Quantité réelle</th>"+
                "<th>échantillon</th>"+
                "<th>defectueux</th>"+
                "<th>Accepter</th>"+
                "</tr> </thead>"+
                "<tbody >  \n";

        ;
        // resultat = resultat + "<td colspan='2'> Commande "+ reception.getCommande().getReference()+"</td>\n";

        for (ControleQualite ctr :reception.getControleQualiteList()) {
            resultat += "         <tr>\n" +
                    " <td >"+ctr.getLigneCommande().getComposante().getName()+" </td>\n" +
                    " <td >"+ctr.getLigneCommande().getPrix()+" </td>\n" +
                    " <td >"+ctr.getLigneCommande().getQuantite()+"</td>\n" +
                    " <td >"+ctr.getQuantiteReel()+"</td>\n" +
                    " <td >"+ctr.getEchantillon()+"</td>\n" +
                    " <td >"+ctr.getDefectueux()+"</td>\n" +
                    " <td >"+ctr.isAccepter()+"</td>\n" +
                    "  </tr>";
        };
        resultat = resultat + "</tbody> </table> </div> </div> </div> </div>\n";




        return resultat;

    }



    @PostMapping("/receptionPdf")
    public void exportToPDF(Reception recpdf, HttpServletResponse response) throws DocumentException, IOException {
        Reception reception = receptionServices.findReceptionById(recpdf.getId());
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = dateFormatter.format(reception.getDate());
        String receptionDate = dateFormatter.format(reception.getDate());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Réception_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        ReceptionPDFGenerateur receptionPDFGenerateur = new ReceptionPDFGenerateur();
        receptionPDFGenerateur.setRef(reception.getReference());
        receptionPDFGenerateur.setDate(receptionDate);
        receptionPDFGenerateur.setCmd(reception.getCommande());
        receptionPDFGenerateur.setControleQualiteList(reception.getControleQualiteList());

        receptionPDFGenerateur.export(response);


    }


}
