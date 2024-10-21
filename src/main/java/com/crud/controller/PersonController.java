package com.crud.controller;

import com.crud.business.PersonBusiness;
import com.crud.dto.PersonDto;
import com.crud.utilities.CustomException;
import com.crud.utilities.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonBusiness personBusiness;

    private Map<String, Object> data;

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> savePerson(@RequestBody Map<String, Object> data) {
        try {
            this.data = Response.getRespose(
                    Response.CODE_OK,
                    Response.STATUS,
                    Response.MENSAJE,
                    1
            );
            return new ResponseEntity<>(this.data, HttpStatus.OK);
        } catch (CustomException ce) {
            throw new CustomException(ce.getMessage(), ce.getHttpStatus());
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<Map<String, Object>> findAllPerson(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        try {
            List<PersonDto> personDtos = this.personBusiness.findAllPerson(page, size).getContent();
            System.out.println(personDtos);
            this.data = Response.getRespose(
                    personDtos,
                    Response.CODE_OK,
                    Response.STATUS,
                    Response.MENSAJE,
                    0,
                    0,
                    0
            );
            return new ResponseEntity<>(this.data, HttpStatus.OK);
        } catch (CustomException ce) {
            throw new CustomException(ce.getMessage(), ce.getHttpStatus());
        }
    }
}
