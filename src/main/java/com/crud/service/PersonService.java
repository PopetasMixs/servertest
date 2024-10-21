package com.crud.service;

import com.crud.entities.Person;
import com.crud.repository.PersonRepository;
import com.crud.service.dao.Idao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements Idao<Person, Long> {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> findAll() { return personRepository.findAll(); }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Person obj) {
        personRepository.save(obj);
    }

    @Override
    public Page<Person> findAll(Pageable pageable) {
        return personRepository.findAll(pageable);
    }
}
