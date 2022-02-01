package com.wms.services;

import com.wms.model.personne.Person;
import com.wms.model.personne.Users;
import com.wms.repository.PersonRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<<<<<<< HEAD

import java.time.LocalDateTime;
=======
>>>>>>> f2b7a46a917d3335c2b16ea6dd0df5d10ac97fd4
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
<<<<<<< HEAD
    //----------- delete
=======
>>>>>>> f2b7a46a917d3335c2b16ea6dd0df5d10ac97fd4
    public void deletePerson(final Long id) {
        personRepository.deleteById(id);
    }

    public Person saveCustomer(Person customer) {
<<<<<<< HEAD
        Optional<Person> person = personRepository.findPersonByName(customer.getName());
        if(person.isEmpty()){
            customer.setRole(true);
            customer.setStatus(true);
            return  personRepository.save(customer);
        }
        return customer;
    }
    public Person saveSupplier(Person supplier) {
        Optional<Person> person = personRepository.findPersonByName(supplier.getName());
        if(person.isEmpty()){
            supplier.setRole(false);
            supplier.setStatus(true);
            return  personRepository.save(supplier);
        }
        return supplier;
    }

    //-------- update
    public void updatePerson(final Long id, Person person) {
        Optional<Person> e = personRepository.findById( id);

        if(e.isPresent() && e.get().isRole() ==  person.isRole() ) {
            String name = person.getName();
            String email = person.getMail();
            String adress = person.getAdress();
            String phone = person.getPhone();
            boolean status = person.isStatus();

            if( name != null || email != null || adress != null || phone != null ||
                    status != e.get().isStatus() ){
                if(name != null) {
                    personRepository.updateNameofPerson(id,name);
                }
                if(email != null) {
                    personRepository.updateMailofPerson(id,email);
                }
                if(adress != null) {
                    personRepository.updateAdressofPerson(id,adress);
                }
                if(phone != null) {
                    personRepository.updatePhoneofPerson(id,phone);
                }
                if(status != e.get().isStatus()) {
                    personRepository.updateStatusofPerson(id,status);
                }
            }
            else{
                System.out.println( "aucunne modification !!! ");
            }

        } else {
            System.out.println( "Error de modification ");

        }

    }
=======
        customer.setRole(true);
        customer.setStatus(true);
        if(personRepository.findPersonByName(customer.getName()) == null){
            return  personRepository.save(customer);
        }
        return customer;
    }
    public Person saveSupplier(Person supplier) {
        supplier.setRole(false);
        supplier.setStatus(true);
        if(personRepository.findPersonByName(supplier.getName()) == null ){
            return  personRepository.save(supplier);
        }
        return supplier;
    }
>>>>>>> f2b7a46a917d3335c2b16ea6dd0df5d10ac97fd4

}
