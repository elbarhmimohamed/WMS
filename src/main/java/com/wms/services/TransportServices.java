package com.wms.services;


import com.wms.model.operation.Transport;
import com.wms.model.personne.Users;
import com.wms.repository.TransportRepository;
import com.wms.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransportServices {

    @Autowired
    TransportRepository transportRepository;



    public Transport getTransportById(final Long id) {
        return transportRepository.findTransportById(id);
    }

    public Iterable<Transport> getTransports() {
        return transportRepository.findAll();
    }

    public  Transport getTransportByMatricule(String matricule){
        Transport transport = transportRepository.findTransportByMatricule(matricule);
        if(transport != null){
            return  transport;
        }
        return null;
    }

    public void deletetransport(final Long id) {
        transportRepository.deleteById(id);
    }

    public Transport saveTransport(Transport transport) {
        Transport transport1 = transportRepository.findTransportByMatricule(transport.getMatricule());
        if(transport1 == null){
            return  transportRepository.save(transport);
        }
        return transport;
    }
}
