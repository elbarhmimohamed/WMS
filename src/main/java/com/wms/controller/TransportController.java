package com.wms.controller;


import com.wms.model.operation.Commande;
import com.wms.model.operation.Transport;
import com.wms.model.personne.Person;
import com.wms.services.TransportServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class TransportController {


    @Autowired
    TransportServices transportServices;

    //--------------- GetPersons -----------



    @GetMapping("/transport")
    public String getTransports(Model model ) {
        Iterable<Transport> transports = transportServices.getTransports();

        model.addAttribute("transports",transports);
        model.addAttribute("transport",new Transport());
        model.addAttribute("transportremove",new Transport());

            return "/page/transport";
    }



    //---------------- Save ---------------------
    @PostMapping("/saveTrnasport")
    public String createTransport(Transport transport, BindingResult result, Model model) {

        transportServices.saveTransport(transport);

        return "redirect:/transport";


    }
    @PostMapping("/transportremove")
    public String transportremove(Transport transport, BindingResult result, Model model) {

        transportServices.deletetransport( transport.getId());

        return "redirect:/transport";


    }




    //---------------- update ------------------------

   /* @PutMapping("/Updatetransport/{id}")
    public Transport updateTransport(@PathVariable("id") final Long id, @RequestBody Transport transport) {
        Transport e = transportServices.getTransportById(id);
        if(e != null) {
            Transport currentTransport = e;

            String mat = transport.getMatricule();
            if(mat != null) {
                currentTransport.setMatricule(mat);
            }


            return currentTransport;
        } else {
            return null;
        }
    }*/

    //--------------- Delete -------------




}
