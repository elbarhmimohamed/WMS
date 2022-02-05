package com.wms.controller;

import com.wms.model.operation.Commande;
import com.wms.model.operation.Operation;
import com.wms.model.operation.Transport;
import com.wms.model.personne.Person;
import com.wms.model.personne.Users;
import com.wms.services.CommandeServices;
import com.wms.services.OperationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Controller
public class CommandeController {


    @Autowired
    CommandeServices commandeServices;


    //--------------- GetPersons -----------

    @GetMapping("/commande")
    public String commandePage(Model model) {
        Iterable<Commande> receptionsCmd = commandeServices.getReceptionCmd();
        model.addAttribute("Commande",receptionsCmd);
        return "/page/commande";
    }

    @GetMapping("/receptionsCmd")
    public String getReceptionsCmd(Model model) {
        Iterable<Commande> receptionsCmd = commandeServices.getReceptionCmd();
        model.addAttribute("receptioncmd",receptionsCmd);
        model.addAttribute("isReceptionCmd", true);
        return "/";
    }
    @GetMapping("/expeditionCmd")
    public String getExpeditionsCmd(Model model) {
        Iterable<Commande> expeditionsCmd = commandeServices.getReceptionCmd();
        model.addAttribute("expeditioncmd",expeditionsCmd);
        model.addAttribute("isReceptionCmd", false);
        return "/";
    }


    @GetMapping("/Commande/{id}")
    public Commande getCommande(@PathVariable("id") final Long id) {
        Optional<Commande> commande = commandeServices.getCommandeById(id);
        if(commande.isPresent()) {
            return commande.get();
        } else {
            return null;
        }
    }
    //---------------- Save ---------------------
    @PostMapping("/saveCommande")
    public Commande createCommande(@RequestBody Commande commande) {
        if(commande.isType()){
            return commandeServices.saveReceptionCmd(commande);
        }

        return commandeServices.saveExepiditionCmd(commande);
    }



    //---------------- update ------------------------
/*
    @PutMapping("/updateCommande/{id}")
    public Commande updateCommande(@PathVariable("id") final Long id, @RequestBody Commande commande) {
        Optional<Commande> e = commandeServices.getCommandeById(id);
        if(e.isPresent()) {
            Commande currentCommande = e.get();

            Date datecmd = commande.getDate();
            if(datecmd != null) {
                currentCommande.setDate(datecmd);
            }
            Boolean typeCmd = commande.isType();
            if(currentCommande.isType() != typeCmd) {
                currentCommande.setType(typeCmd);
            }
            Users user = commande.getUser();
            if (user != null){
                currentCommande.setUser(user);
            }
            Person person = commande.getPerson();
            if (person != null){
                currentCommande.setPerson(person);
            }

            Collection <Commande> cmd =  commande.getPerson().getCommandes();
            if(cmd != null){
                currentOperation.setCommande(cmd);
            }
            Transport transport = operation.getTransport();
            if (transport != null){
                currentOperation.setTransport(transport);
            }
            if(currentOperation.isType()){
                operationServices.saveExepidition(currentOperation);
            }
            else {
                operationServices.saveReception(currentOperation);
            }

            return currentOperation;
        } else {
            return null;
        }
    }
*/
    //--------------- Delete -------------

    @DeleteMapping("/deleteCommande/{id}")
    public void deleteCommande(@PathVariable("id") final Long id) {
        commandeServices.deleteCommande(id);
    }
}
