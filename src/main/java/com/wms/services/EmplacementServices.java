package com.wms.services;

import com.wms.model.emplacement.Emplacement;
import com.wms.model.personne.Users;
import com.wms.repository.EmplacementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmplacementServices {

    @Autowired
    public EmplacementRepository emplacementRepository;

    public List<Emplacement> getAllEmplacement() {
        return emplacementRepository.findAll();
    }




}
