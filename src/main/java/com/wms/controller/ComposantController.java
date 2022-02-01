package com.wms.controller;

import com.wms.model.stock.Composante;
import com.wms.services.ComposantServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ComposantController {

    @Autowired
    private ComposantServices composantServices;

/*
    @PostMapping("/CreateComposant")
    public Composante createComposant(@RequestBody Composante composante) {
        return composantServices.saveComposante(composante);
    }
*/
    @GetMapping("/composants/{id}")
    public Composante getComposants(@PathVariable("id") final Long id) {
        Optional<Composante> comp = composantServices.getComposanteById(id);
        if(comp.isPresent()) {
            return comp.get();
        } else {
            return null;
        }
    }

    @GetMapping("/cmposants")
    public Iterable<Composante> getComposants() {
        return composantServices.getAllComposants();
    }


    @PutMapping("/updatecomposant/{id}")
    public Composante updateComposant(@PathVariable("id") final Long id, @RequestBody Composante composante) {
        Optional<Composante> e = composantServices.getComposanteById(id);
        if(e.isPresent()) {
            Composante currentComposant = e.get();

            String name = composante.getName();
            if(name != null) {
                currentComposant.setName(name);
            }
            Long seuil = composante.getSeuil();
            if (seuil != 0 ){
                currentComposant.setSeuil(seuil);
            }
            Long qte = composante.getQuantity();
            if (qte != 0 ){
                currentComposant.setQuantity(seuil);
            }
            Boolean type = composante.isType();
            if(type != currentComposant.isType()) {
                currentComposant.setType(type);;
            }



            composantServices.saveComposante(currentComposant);
            return currentComposant;
        } else {
            return null;
        }
    }



    @DeleteMapping("/deletecomposant/{id}")
    public void deleteComposant(@PathVariable("id") final Long id) {
        composantServices.deleteComposante(id);
    }

}
