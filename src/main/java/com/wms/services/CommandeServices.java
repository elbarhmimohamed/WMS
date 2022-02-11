package com.wms.services;

import com.wms.model.operation.Commande;
import com.wms.model.operation.LigneCommande;
import com.wms.model.personne.Person;
import com.wms.model.stock.Composante;
import com.wms.repository.CommandeRepository;
import com.wms.repository.ComposantRepository;
import com.wms.repository.LigneCommandeRepository;
import com.wms.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommandeServices {
    @Autowired
    PersonServices personServices;
    @Autowired
    ComposantRepository composantRepository;
    @Autowired
    CommandeRepository commandeRepository;
    @Autowired
    LigneCommandeRepository ligneCommandeRepository;

    public List<Person> findAllfournisseur() {
        return personServices.getFournisseurs();
    }


    public List<Composante> findAllcomposant() {
        return composantRepository.findAll();
    }

    public List<Commande> findAllCommande() {
        return commandeRepository.findAll();
    }

    public Commande findCommandeById(int id){
        return commandeRepository.findCommandeBy(id);
    }

    public void saveCommande(Commande commande) {

        for (LigneCommande ligneCommande : commande.getLigneCommande()) {
            ligneCommandeRepository.save(ligneCommande);
        }
        commandeRepository.save(commande);



    }



}
