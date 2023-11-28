package com.olbnar.smarteletron.controllers.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olbnar.smarteletron.models.Usuario.PersonModel;
import com.olbnar.smarteletron.records.Person.PersonRecord;
import com.olbnar.smarteletron.services.Person.PersonService;

@RestController
@CrossOrigin(origins = "*")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public Page<PersonRecord> getAllPersons(@RequestParam int page) {
        return personService.getAllPersons(page);
    }

    @PostMapping("/persons")
    public PersonModel savePerson(@RequestBody PersonModel personModel) {
        return personService.savePerson(personModel);
    }
}
