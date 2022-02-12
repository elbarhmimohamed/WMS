package com.wms.services;

import com.wms.model.operation.Reception;
import com.wms.repository.ReceptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;

@Service
public class ReceptionServices {
    @Autowired
    ReceptionRepository receptionRepository;

    @Transactional
    public Reception findReceptionById(int id){
        return  receptionRepository.findReceptionByiD(id);
    }

    public int numberOfReception(){
        return Math.toIntExact(receptionRepository.count());
    }
}
