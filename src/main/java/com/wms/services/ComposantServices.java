package com.wms.services;

import com.wms.model.emplacement.Emplacement;
import com.wms.model.stock.Categorie;
import com.wms.model.stock.Composante;
import com.wms.repository.CategoriesRepository;
import com.wms.repository.ComposantRepository;
import com.wms.repository.EmplacementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        //Optional<Composante> comp = composantRepository.findComposanteByName(composante.getName());
        //if(comp == null){
            return  composantRepository.save(composante);

        //return  composante;


    }
}
