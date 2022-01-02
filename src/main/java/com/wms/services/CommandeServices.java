package com.wms.services;


import com.wms.model.operation.Commande;
import com.wms.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommandeServices {

    @Autowired
    private CommandeRepository commandeRepository;

    /// ------- get all reception -----------
    public Iterable<Commande> getReceptionCmd() {
        return commandeRepository.findAllReceptionCommande();
    }
    /// ------- get all expedition -----------
    public Iterable<Commande> getExpetionCmd() {
        return commandeRepository.findAllExpeditionCommande();
    }
    //------- find by id ------------

    public Optional<Commande> getCommandeById(final Long id) {
        return commandeRepository.findById(id);
    }

    //-------------- delete

    public void deleteCommande(final Long id) {
        commandeRepository.deleteById(id);
    }
    // -------------   Create
    public Commande saveReceptionCmd(Commande commande) {
        commande.setType(false);
        Commande savedCMD = commandeRepository.save(commande);
        return  savedCMD;


    }

    public Commande saveExepiditionCmd(Commande commande) {
        commande.setType(true);
        Commande savedCMD = commandeRepository.save(commande);
        return  savedCMD;


    }

}
