package com.wms.controller;

import com.wms.model.operation.Commande;
import com.wms.model.operation.Operation;
import com.wms.model.operation.Transport;
import com.wms.services.OperationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@Controller
public class OperationController {

    @Autowired
    OperationServices operationServices;

/*
    //--------------- GetPersons -----------

    @GetMapping("/receptions")
    public String getReceptions(Model model) {
        Iterable<Operation> receptions = operationServices.getReception();
        model.addAttribute("reception",receptions);
        model.addAttribute("isReception", true);
        return "/";
    }
    @GetMapping("/expedition")
    public String getExpeditions(Model model) {
        Iterable<Operation> expeditions = operationServices.getExpetion();
        model.addAttribute("expedition",expeditions);
        model.addAttribute("isReception", false);
        return "/";
    }


    @GetMapping("/operation/{id}")
    public Operation getOperatin(@PathVariable("id") final Long id) {
        Optional<Operation> operation = operationServices.getOperationById(id);
        if(operation.isPresent()) {
            return operation.get();
        } else {
            return null;
        }
    }
    //---------------- Save ---------------------
    @PostMapping("/saveOperation")
    public Operation createOperation(@RequestBody Operation operation) {
        if(operation.isType()){
            return operationServices.saveExepidition(operation);
        }

        return operationServices.saveExepidition(operation);
    }



    //---------------- update ------------------------

    @PutMapping("/updateOperation/{id}")
    public Operation updateOperation(@PathVariable("id") final Long id, @RequestBody Operation operation) {
        Optional<Operation> e = operationServices.getOperationById(id);
        if(e.isPresent()) {
            Operation currentOperation = e.get();
            Date dateoperation = operation.getDate();
            if(dateoperation != null) {
                currentOperation.setDate(dateoperation);
            }
            Boolean tyoeoOperation = operation.isType();
            if(currentOperation.isType() != tyoeoOperation) {
                currentOperation.setType(tyoeoOperation);
            }
            Commande cmd = operation.getCommande();
            if(cmd != null){
                currentOperation.setCommande(cmd);
            }
            Transport transport = operation.getTransport();
            if (transport != null){
                currentOperation.setTransport(transport);
            }
            if(currentOperation.isType()){
                operationServices.saveExepidition(currentOperation);
            }
            else {
                operationServices.saveReception(currentOperation);
            }

            return currentOperation;
        } else {
            return null;
        }
    }

    //--------------- Delete -------------

    @DeleteMapping("/deleteOperation/{id}")
    public void deleteOperation(@PathVariable("id") final Long id) {
        operationServices.deleteOperation(id);
    }
*/
}
