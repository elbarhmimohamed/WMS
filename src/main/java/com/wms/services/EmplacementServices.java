package com.wms.services;

import com.wms.model.emplacement.Emplacement;
import com.wms.model.operation.Operation;
import com.wms.model.personne.Users;
import com.wms.repository.EmplacementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        if(id != null) {
            return emplacementRepository.findById(id);
        }
        return  null;
    }

    public Optional<Emplacement> getEmplacemetByRef(final String ref){
        if(ref != null) {
            return emplacementRepository.findByrefemplacement(ref);
        }
        return  null;
    }

    public void deleteEmplacemet(final Long id) {
        if(id != null && emplacementRepository.findById(id) != null ) {
            emplacementRepository.deleteById(id);
        }
    }
    // -------------   Create
    public Emplacement saveEmplacement(Emplacement emplacement) {
        Optional<Emplacement> emp = emplacementRepository.findByrefemplacement(emplacement.getRefemplacement());
        if(emp == null){
            return  emplacementRepository.save(emplacement);
        }
            return  emplacement;


    }
    //----------------- update

    public void updateEmplacement(final Long id, Emplacement emp) {
        Optional<Emplacement> e = emplacementRepository.findById( id);

        if(e.isPresent()) {
            String ref = emp.getRefemplacement();
                if(ref != null) {
                    emplacementRepository.updateRefofEmplacement(id,ref);
                }
            }
        else{
                System.out.println( "aucunne modification !!! ");
        }

    }




}
