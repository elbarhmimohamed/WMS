package com.wms.services;

import com.wms.model.operation.Commande;
import com.wms.model.operation.Operation;
import com.wms.model.operation.Transport;
import com.wms.model.personne.Person;
import com.wms.model.stock.Categorie;
import com.wms.repository.OperationRepository;
import com.wms.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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

    public Operation getOperationById(final Long id) {
        return operationRepository.findOperationById(id);
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

    public void updateOperation(final Long id, Operation operation) {
        Optional<Operation> e = operationRepository.findById( id);

        if(e.isPresent()) {
            Date date = operation.getDate();
            Commande cmd = operation.getCommande();
            Transport transport = operation.getTransport();


            if(date != null ) {
                operationRepository.updateDateOfOperation(id,date);
            }
            if(cmd != null ) {
                operationRepository.updatecommandeOfOperation(id,cmd);
            }

            if(transport != null ) {
                operationRepository.updateTransportOfOperation(id,transport);
            }

        } else {
            System.out.println( "Error de modification ");

        }

    }


}
