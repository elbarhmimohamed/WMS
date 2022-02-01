package com.wms.services;


import com.wms.model.emplacement.Emplacement;
import com.wms.model.stock.Categorie;
import com.wms.repository.CategoriesRepository;
import com.wms.repository.EmplacementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieServices {
    @Autowired
    public CategoriesRepository categoriesRepository;

    public List<Categorie> getAllCategories() {
        return categoriesRepository.findAll();
    }


    public Optional<Categorie> getCategorieById(final Long id){ return categoriesRepository.findById(id);}
    public Optional<Categorie> getCategorieByName(final String name){ return categoriesRepository.findCategorieByName(name);}

    public void deleteCategories(final Long id) {
        categoriesRepository.deleteById(id);
    }
    // -------------   Create
    public Categorie saveCategories(Categorie categorie) {
        Optional<Categorie> cat = categoriesRepository.findCategorieByName(categorie.getName());
        if(cat == null){
            return  categoriesRepository.save(categorie);
        }
        return  categorie;


    }
}
