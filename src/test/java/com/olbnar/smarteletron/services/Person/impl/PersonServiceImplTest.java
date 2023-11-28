package com.olbnar.smarteletron.services.Person.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
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

     /**
     * Test the getAllPersons method.
     * This test checks if the method returns the correct page of PersonRecord.
     */
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

    /**
     * Test the getPersonModelById method.
     * This test checks if the method returns the correct PersonModel when the person is found.
     */
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

    /**
     * Test the getPersonModelById method.
     * This test checks if the method returns the correct PersonModel when the person is found.
     */
    @Test
    public void testGetPersonModelById_PersonNotFound() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(personRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> personService.getPersonModelById(id));
    }

    /**
     * Test the getPersonModelById method when the ID is null.
     * This test checks if the method throws a NullPointerException when null is passed as the ID.
     */
    @Test
    public void testGetPersonModelById_IdIsNull() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> personService.getPersonModelById(null));
    }

    /**
     * Test the savePerson method when the person already exists.
     * This test checks if the method throws a RuntimeException when trying to save a person that already exists.
     */
    @Test
    public void testSavePerson_PersonExists() {
        // Arrange
        PersonModel personModel = new PersonModel();
        personModel.setId(UUID.randomUUID());
        when(personRepository.existsById(personModel.getId())).thenReturn(true);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> personService.savePerson(personModel));
    }

    /**
     * Test the savePerson method when the person does not exist.
     * This test checks if the method correctly saves a new person when the person does not already exist.
     * It verifies that the username of the saved person is the same as the username of the original person,
     * and that the password of the saved person is correctly encoded.
     */
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

    /**
     * Test the savePerson method when the PersonModel is null.
     * This test checks if the method throws a NullPointerException when null is passed as the PersonModel.
     */
    @Test
    public void testSavePerson_PersonModelIsNull() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> personService.savePerson(null));
    }
}
