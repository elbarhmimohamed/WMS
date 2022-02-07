package com.wms.services;

import com.wms.model.operation.Inventaire;
import com.wms.model.personne.Users;
import com.wms.model.stock.Inventaire_composante;
import com.wms.repository.InventaireRepository;
import com.wms.repository.Inventaire_composanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class InventaireServices {

    @Autowired
    InventaireRepository inventaireRepository;


    @Autowired
    Inventaire_composanteRepository inventaireComposanteRepository;


    public Inventaire getInventaireById(final long id) {


        return inventaireRepository.findInventaireByID(Long.valueOf(id));
    }

    public Iterable<Inventaire> getAllInventaire() {
        return inventaireRepository.findAll();
    }




    public void deleteById(final long id) {

        inventaireRepository.deleteById(id);
    }

    public void saveInventaire(Inventaire inventaire) {

        List<Inventaire_composante> inventaire_composanteList = inventaire.getInventaire_composantes();
       for (int i = 0 ; i < inventaire_composanteList.size() ; i++) {
           inventaire_composanteList.get(i).setEcart(inventaire_composanteList.get(i).getQuantityInReality() - inventaire_composanteList.get(i).getComposante().getQuantity());
           inventaireComposanteRepository.save(inventaire_composanteList.get(i));
       }
        inventaireRepository.save(inventaire);



    }


    public void updateInventaire(final Long id, Inventaire inventaire) {
        Optional<Inventaire> e = inventaireRepository.findById( id);

        if(e.isPresent()) {
            Date date = inventaire.getDate();
            Users user  = inventaire.getUser();
            List<Inventaire_composante> inventaire_composantes = inventaire.getInventaire_composantes();

            if(date != null){
                inventaireRepository.updateDateofInventaire(id,date);
            }
            if(user != null){
                inventaireRepository.updateUsreofInventaire(id,user);
            }
            if(inventaire_composantes != null){
                inventaireRepository.updateInvCompofInventaire(id,inventaire_composantes);
            }

        } else {
            System.out.println( "Error de modification ");

        }

    }




}
