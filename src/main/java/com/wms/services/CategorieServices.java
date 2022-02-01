package com.wms.services;


import com.wms.model.emplacement.Emplacement;
import com.wms.model.personne.Users;
import com.wms.model.stock.Categorie;
import com.wms.repository.CategoriesRepository;
import com.wms.repository.EmplacementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        if(cat.isEmpty()){
            return  categoriesRepository.save(categorie);
        }
        return  categorie;
    }

    public void updateCategories(final Long id, Categorie categorie) {
        Optional<Categorie> e = categoriesRepository.findById( id);

        if(e.isPresent()) {
            String name = categorie.getName();
            String desc = categorie.getDescription();

            if(name != null || desc != null  ){
                if(name != null) {
                    categoriesRepository.updateNameofCat(id,name);
                }
                if(desc != null) {
                    categoriesRepository.updateDescofCat(id,desc);
                }

            }
            else{
                System.out.println( "aucunne modification !!! ");
            }

        } else {
            System.out.println( "Error de modification ");

        }

    }


}
