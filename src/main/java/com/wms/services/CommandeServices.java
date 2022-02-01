package com.wms.services;


import com.wms.model.operation.Commande;
<<<<<<< HEAD
import com.wms.model.personne.Person;
import com.wms.model.personne.Users;
import com.wms.model.stock.Composante;
=======
>>>>>>> f2b7a46a917d3335c2b16ea6dd0df5d10ac97fd4
import com.wms.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
=======
>>>>>>> f2b7a46a917d3335c2b16ea6dd0df5d10ac97fd4
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

<<<<<<< HEAD
    public void updateCmd(final Long id, Commande cmd) {
        Optional<Commande> e = commandeRepository.findById( id);

        if(e.isPresent()) {
            Date date = cmd.getDate();
            boolean type = cmd.isType();
            Person person = cmd.getPerson();
            Collection<Composante> composantes = cmd.getComposantes();

            if(date != null || type != e.get().isType() || person != null || !composantes.isEmpty() ){
                if(date != null) {
                    commandeRepository.updateDateofCmd(id,date);
                }
                if(person  != null) {
                    commandeRepository.updatePersonofCmd(id,person);
                }
                if(!composantes.isEmpty()) {
                    commandeRepository.updateComposantesofCmd(id,composantes);
                }
                if(type != e.get().isType()) {
                    commandeRepository.updateTypeofCmd(id,type);
                }
            }
            else{
                System.out.println( "aucunne modification !!! ");
            }

        } else {
            System.out.println( "Error de modification ");

        }

    }


=======
>>>>>>> f2b7a46a917d3335c2b16ea6dd0df5d10ac97fd4
}
