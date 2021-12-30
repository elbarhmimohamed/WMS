package com.wms.services;

import com.wms.model.personne.Person;
import com.wms.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class PersonServices {


    @Autowired
    private PersonRepository personRepository;

    public Optional<Person> getPerson(final Long id) {
        return personRepository.findById(id);
    }

    public Iterable<Person> getPersons() {
        return personRepository.findAll();
    }

    public void deletePerson(final Long id) {
        personRepository.deleteById(id);
    }

    public Person savePerson(Person person) {
        Person savedPerson = personRepository.save(person);
        return savedPerson;
    }
}
