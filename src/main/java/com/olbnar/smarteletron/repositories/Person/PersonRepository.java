package com.olbnar.smarteletron.repositories.Person;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.olbnar.smarteletron.models.Usuario.PersonModel;
import com.olbnar.smarteletron.records.Person.PersonRecord;

@Repository
public interface PersonRepository extends JpaRepository<PersonModel, UUID> {
    
    @Query(value = "SELECT name, username, profileimageurl, isactive, isnotlocked FROM users", nativeQuery = true)
    Page<PersonRecord> findPersonRecord(Pageable pageable);
    

}
