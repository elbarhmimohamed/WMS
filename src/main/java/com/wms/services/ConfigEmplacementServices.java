package com.wms.services;

import com.wms.model.emplacement.ConfigEmplacement;
import com.wms.model.emplacement.Emplacement;
import com.wms.repository.ConfigEmplacementRepository;
import com.wms.repository.EmplacementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConfigEmplacementServices {
    @Autowired
    public ConfigEmplacementRepository configEmplacementRepository;

    public List<ConfigEmplacement> getAllConfigEmplacement() {
        return configEmplacementRepository.findAll();
    }

    public Optional<ConfigEmplacement> getConfigEmplacementfinal( Long id){

        return configEmplacementRepository.findById(id);
    }

    public void deleteConfigEmplacement(final Long id) {
        configEmplacementRepository.deleteById(id);
    }
    // -------------   Create
    public ConfigEmplacement saveConfigEmplacement(ConfigEmplacement configEmplacement) {

            return  configEmplacementRepository.save(configEmplacement);



    }
}
