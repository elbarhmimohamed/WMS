package com.wms.controller;


import com.wms.model.operation.Transport;
import com.wms.model.personne.Person;
import com.wms.services.TransportServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class TransportController {


    @Autowired
    TransportServices transportServices;

    //--------------- GetPersons -----------

    @GetMapping("/trasport")
    public Iterable<Transport> getTransports( ) {
        Iterable<Transport> transports = transportServices.getTransports();
        return  transports;
    }


    @GetMapping("/transport/{id}")
    public Transport getTransport(@PathVariable("id") final Long id) {
        Optional<Transport> transport = transportServices.getTransport(id);
        if(transport.isPresent()) {
            return transport.get();
        } else {
            return null;
        }
    }
    //---------------- Save ---------------------
    @PostMapping("/saveTrnasport")
    public Transport createTransport(@RequestBody Transport transport) {

        return transportServices.saveTransport(transport);

    }



    //---------------- update ------------------------

    @PutMapping("/Updatetransport/{id}")
    public Transport updateTransport(@PathVariable("id") final Long id, @RequestBody Transport transport) {
        Optional<Transport> e = transportServices.getTransport(id);
        if(e.isPresent()) {
            Transport currentTransport = e.get();

            String mat = transport.getMatricule();
            if(mat != null) {
                currentTransport.setMatricule(mat);
            }


            return currentTransport;
        } else {
            return null;
        }
    }

    //--------------- Delete -------------

    @DeleteMapping("/deleteTransport/{id}")
    public void deleteTransport(@PathVariable("id") final Long id) {
        transportServices.deletetransport(id);
    }


}
