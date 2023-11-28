package com.olbnar.smarteletron.services.Person;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.olbnar.smarteletron.models.Usuario.PersonModel;
import com.olbnar.smarteletron.records.Person.PersonRecord;

public interface PersonService {
    
      public Page<PersonRecord> getAllPersons(int pageNum);

      public PersonModel getPersonModelById(UUID id);

      public PersonModel savePerson(PersonModel personModel);
}
