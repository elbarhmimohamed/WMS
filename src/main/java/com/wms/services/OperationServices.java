package com.wms.services;

import com.wms.model.operation.Operation;
import com.wms.model.personne.Person;
import com.wms.repository.OperationRepository;
import com.wms.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperationServices {

    @Autowired
    private OperationRepository operationRepository;

    /// ------- get all reception -----------
    public Iterable<Operation> getReception() {
        return operationRepository.findAllReception();
    }
        /// ------- get all expedition -----------
        public Iterable<Operation> getExpetion() {
            return operationRepository.findAllExpedition();
        }
    //------- find by id ------------

    public Optional<Operation> getOperationById(final Long id) {
        return operationRepository.findById(id);
    }

    //-------------- delete

    public void deleteOperation(final Long id) {
        operationRepository.deleteById(id);
    }
        // -------------   Create
    public Operation saveReception(Operation operation) {
        operation.setType(false);
        Operation savedoperation = operationRepository.save(operation);
        return  savedoperation;


    }
    public Operation saveExepidition(Operation operation) {
        operation.setType(true);
        Operation savedoperation = operationRepository.save(operation);
        return  savedoperation;


    }


}
