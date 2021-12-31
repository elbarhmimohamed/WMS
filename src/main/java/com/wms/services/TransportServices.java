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



    public Optional<Transport> getTransport(final Long id) {
        return transportRepository.findById(id);
    }

    public Iterable<Transport> getTransports() {
        return transportRepository.findAll();
    }

    public void deletetransport(final Long id) {
        transportRepository.deleteById(id);
    }

    public Transport saveTransport(Transport transport) {
        Transport savedTransport = transportRepository.save(transport);
        return  savedTransport;
    }
}
