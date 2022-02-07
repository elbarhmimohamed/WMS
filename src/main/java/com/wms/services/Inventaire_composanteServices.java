package com.wms.services;

import com.wms.model.operation.Inventaire;
import com.wms.model.stock.Categorie;
import com.wms.model.stock.Composante;
import com.wms.model.stock.Inventaire_composante;
import com.wms.repository.Inventaire_composanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class Inventaire_composanteServices {

    @Autowired
    Inventaire_composanteRepository inventaire_composanteRepository;



    public Inventaire_composante getInvCompByID(final Long id) {
        return inventaire_composanteRepository.findInventaire_composanteByID(id);
    }

    public Iterable<Inventaire_composante> getAllInvComp() {
        return inventaire_composanteRepository.findAll();
    }



    public void updateInvofInvcomp(long id,Inventaire inventaire){
        inventaire_composanteRepository.updateInventaireofInventaire_composante(id,inventaire);
    }
    public void deleteById(final Long id) {
        inventaire_composanteRepository.deleteById(id);
    }

    public Inventaire_composante saveInvComp(Inventaire_composante inventaire_composante) {
            inventaire_composante.setEcart(inventaire_composante.getQuantityInReality() - inventaire_composante.getComposante().getQuantity());
            return  inventaire_composanteRepository.save(inventaire_composante);


    }
    public List<Inventaire_composante> saveAllInvComp(Collection<Inventaire_composante> inventaire_composantes) {

        return  inventaire_composanteRepository.saveAll(inventaire_composantes);

    }

    public void updateInvComp(final Long id, Inventaire_composante inventaire_composante) {
        Optional<Inventaire_composante> e = inventaire_composanteRepository.findById( id);

        if(e.isPresent()) {
            Composante composante = inventaire_composante.getComposante();

            long qtereality = inventaire_composante.getQuantityInReality();
            long ecart = 0;


            if(composante != null && composante != inventaire_composanteRepository.findInventaire_composanteByID(id).getComposante() ) {
                inventaire_composanteRepository.updateComposanteofInventaire_composante(id,composante);
            }
            if(qtereality > 0 ) {
                ecart =  0;
                inventaire_composanteRepository.updateQteinRealityofInventaire_composante(id,qtereality);
                ecart = inventaire_composanteRepository.findInventaire_composanteByID(id).getComposante().getQuantity() - qtereality;
                inventaire_composanteRepository.updateEcartofInventaire_composante(id,ecart);
            }

        } else {
            System.out.println( "Error de modification ");

        }

    }



}
