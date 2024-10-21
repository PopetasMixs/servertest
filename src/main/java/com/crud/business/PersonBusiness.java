package com.crud.business;

import com.crud.dto.PersonDto;
import com.crud.entities.Person;
import com.crud.service.PersonService;
import com.crud.utilities.CustomException;
import com.crud.utilities.Util;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class PersonBusiness {

    @Autowired
    private PersonService personService;

    private final ModelMapper modelMapper = new ModelMapper();

    private Boolean validatePerson(long id) {

        try {
            List<Person> personList = this.personService.findAll();
            Person person = personList.stream()
                    .filter(person1 -> person1.getId() == id)
                    .findAny()
                    .orElse(null);
            return person != null;
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    public Boolean savePerson(Map<String, Object> data) {

        try {
            JSONObject dataObject = Util.getdata(data);
            System.out.println(dataObject);

            PersonDto personDto = new PersonDto();
            personDto.setId(0L);
            personDto.setName(dataObject.getString("name"));
            personDto.setLastName(dataObject.getString("last_name"));
            personDto.setEmail(dataObject.getString("email"));

            if (validatePerson(personDto.getId())){
                throw new RuntimeException("Person already exists");
            } else {
                Person person = modelMapper.map(personDto, Person.class);
                this.personService.save(person);
                return true;
            }
        } catch (CustomException ce) {
            throw new CustomException(ce.getMessage(), ce.getHttpStatus());
        }
    }

    public Page<PersonDto> findAllPerson(int page, int size) {

        try {
            PageRequest pageRequest = PageRequest.of(page, size);
            Page<Person> personPage = this.personService.findAll(pageRequest);
            if (personPage.isEmpty()) {
                return Page.empty();
            }
            return personPage.map(Person -> modelMapper.map(Person, PersonDto.class));
        } catch (CustomException ce) {
            throw new CustomException("Error", ce.getHttpStatus());
        }
    }

    public PersonDto findPersonById(long id) {
        try {
            Person person = this.personService.findById(id);
            return modelMapper.map(person, PersonDto.class);
        } catch (CustomException ce) {
            throw new CustomException("Error", ce.getHttpStatus());
        }
    }
}
