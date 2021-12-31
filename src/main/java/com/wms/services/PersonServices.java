package com.wms.services;

import com.wms.model.personne.Person;
import com.wms.repository.PersonRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Data
@Service
public class PersonServices {


    @Autowired
    private PersonRepository personRepository;

    /// ------- get all person -----------
    public Iterable<Person> getClients() {
        return personRepository.findAllCustomers();
    }
    public Iterable<Person> getFournisseurs() {
        return personRepository.findAllSuppliers();
    }

    //------- find by id ------------

    public Optional<Person> getPerson(final Long id) {
        return personRepository.findById(id);
    }
    public void deletePerson(final Long id) {
        personRepository.deleteById(id);
    }

    public Person saveCustomer(Person customer) {
        customer.setRole(true);
        customer.setStatus(true);
        Person savedPerson = personRepository.save(customer);
        return savedPerson;
    }
    public Person saveSupplier(Person supplier) {
        supplier.setRole(false);
        supplier.setStatus(true);
        Person savedPerson = personRepository.save(supplier);
        return savedPerson;
    }

}
