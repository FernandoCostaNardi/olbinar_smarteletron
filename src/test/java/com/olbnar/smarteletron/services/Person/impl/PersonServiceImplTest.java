package com.olbnar.smarteletron.services.Person.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.olbnar.smarteletron.models.Usuario.PersonModel;
import com.olbnar.smarteletron.records.Person.PersonRecord;
import com.olbnar.smarteletron.repositories.Person.PersonRepository;

public class PersonServiceImplTest {
    
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void testGetAllPersons() {
        // Arrange
        int pageNum = 0;
        Pageable pageable = PageRequest.of(pageNum, 10);
        PersonRecord personRecord = mock(PersonRecord.class); // Corrected this line
        Page<PersonRecord> expectedPage = new PageImpl<>(Collections.singletonList(personRecord), pageable, 1);
        when(personRepository.findPersonRecord(pageable)).thenReturn(expectedPage);

        // Act
        Page<PersonRecord> actualPage = personService.getAllPersons(pageNum);

        // Assert
        assertEquals(expectedPage, actualPage);
    }

    @Test
    public void testGetPersonModelById_PersonFound() {
        // Arrange
        UUID id = UUID.randomUUID();
        PersonModel expectedPerson = new PersonModel(); // Substitua isso com a criação real do seu PersonModel
        when(personRepository.findById(id)).thenReturn(Optional.of(expectedPerson));

        // Act
        PersonModel actualPerson = personService.getPersonModelById(id);

        // Assert
        assertEquals(expectedPerson, actualPerson);
    }

    @Test
    public void testGetPersonModelById_PersonNotFound() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(personRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> personService.getPersonModelById(id));
    }

    @Test
    public void testGetPersonModelById_IdIsNull() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> personService.getPersonModelById(null));
    }

    @Test
    public void testSavePerson_PersonExists() {
        // Arrange
        PersonModel personModel = new PersonModel();
        personModel.setId(UUID.randomUUID());
        when(personRepository.existsById(personModel.getId())).thenReturn(true);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> personService.savePerson(personModel));
    }

    @Test
    public void testSavePerson_PersonDoesNotExist() {
        // Arrange
        PersonModel personModel = new PersonModel();
        personModel.setUsername("username");
        personModel.setPassword("password");
        when(personRepository.existsByUsername(personModel.getUsername())).thenReturn(null);
        when(passwordEncoder.encode(personModel.getPassword())).thenReturn("encodedPassword");
        when(personRepository.save(any(PersonModel.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        PersonModel savedPerson = personService.savePerson(personModel);

        // Assert
        assertEquals(personModel.getUsername(), savedPerson.getUsername());
        assertEquals("encodedPassword", savedPerson.getPassword());
    }

    @Test
    public void testSavePerson_PersonModelIsNull() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> personService.savePerson(null));
    }
}
