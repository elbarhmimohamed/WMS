package com.wms.services;

import com.wms.model.emplacement.Emplacement;
import com.wms.model.personne.Person;
import com.wms.model.personne.Users;
import com.wms.model.stock.Categorie;
import com.wms.model.stock.Composante;
import com.wms.repository.CategoriesRepository;
import com.wms.repository.ComposantRepository;
import com.wms.repository.EmplacementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ComposantServices {
    @Autowired
    public ComposantRepository composantRepository;

    public List<Composante> getAllComposants() {
        return composantRepository.findAll();
    }

    public Optional<Composante> getComposanteById(final Long id){ return composantRepository.findById(id);}
    public Optional<Composante> getComposanteByName(final String name){ return composantRepository.findComposanteByName(name);}

    public void deleteComposante(final Long id) { composantRepository.deleteById(id);}

    // -------------   Create

    public Composante saveComposante(Composante composante) {
        Optional<Composante> comp = composantRepository.findComposanteByName(composante.getName());
        if(comp.isEmpty()){
            composante.setType(false);
            return  composantRepository.save(composante);
        }
        return composante;
    }
    public Composante saveProduct(Composante composante) {
        Optional<Composante> comp = composantRepository.findComposanteByName(composante.getName());
        if(comp.isEmpty()){
            composante.setType(true);
            return  composantRepository.save(composante);
        }
        return composante;
    }
    //------------------- Edit
    public void updateComposante(final Long id, Composante composante) {
        Optional<Composante> e = composantRepository.findById( id);

        if(e.isPresent()) {
            String name = composante.getName();
                if(name != null) {
                    composantRepository.updateNameofComposante(id,name);
                }

            long qte = composante.getQuantity();
                if(qte != 0) {
                    composantRepository.updateQuantityofComposante(id,qte);
                }

            long seuil = composante.getSeuil();
                if(seuil != 0) {
                    composantRepository.updateSeuilofComposante(id,seuil);
                }
            Categorie cat = composante.getCategorie();
                if(cat != null){
                    composantRepository.updateCatofComposante(id,cat);
                }

            }

        else {
            System.out.println( "Error de modification ");

        }

    }
}
