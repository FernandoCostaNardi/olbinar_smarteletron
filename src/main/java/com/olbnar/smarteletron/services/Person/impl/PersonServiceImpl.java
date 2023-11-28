package com.olbnar.smarteletron.services.Person.impl;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.olbnar.smarteletron.models.Usuario.PersonModel;
import com.olbnar.smarteletron.records.Person.PersonRecord;
import com.olbnar.smarteletron.repositories.Person.PersonRepository;
import com.olbnar.smarteletron.services.Person.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Page<PersonRecord> getAllPersons(int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, 10);
        return personRepository.findPersonRecord(pageable);
    }

    @Override
    public PersonModel getPersonModelById(UUID id) {
        return personRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Pessoa não encontrada com o ID: " + id));
    }

    @Override
    public PersonModel savePerson(PersonModel personModel) {
        if(personRepository.existsByUsername(personModel.getUsername())){
            throw new RuntimeException("Pessoa já existe");
        }
        personModel.setPassword(passwordEncoder.encode(personModel.getPassword()));
        return personRepository.save(personModel);
    }      
}
