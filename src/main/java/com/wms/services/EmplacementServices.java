package com.wms.services;

import com.wms.model.emplacement.Emplacement;
import com.wms.model.operation.Operation;
import com.wms.model.personne.Users;
import com.wms.repository.EmplacementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmplacementServices {

    @Autowired
    public EmplacementRepository emplacementRepository;

    public List<Emplacement> getAllEmplacement() {
        return emplacementRepository.findAll();
    }

    public Optional<Emplacement> getEmplacemet(final Long id){

        return emplacementRepository.findById(id);
    }

    public void deleteEmplacemet(final Long id) {
        emplacementRepository.deleteById(id);
    }
    // -------------   Create
    public Emplacement saveEmplacement(Emplacement emplacement) {
        Emplacement emp = emplacementRepository.findByrefemplacement(emplacement.getRefemplacement());
        if(emp == null){
            return  emplacementRepository.save(emplacement);
        }
            return  emplacement;


    }



}
