package one.digitalinnovation.ivanilson.personapi.controller;

import one.digitalinnovation.ivanilson.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.ivanilson.personapi.dto.request.PersonDTO;
import one.digitalinnovation.ivanilson.personapi.exception.PersonalNotFoundException;
import one.digitalinnovation.ivanilson.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO){
        return personService.createPerson(personDTO);
    }

    @GetMapping
    public List<PersonDTO> listAll(){
        return personService.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws PersonalNotFoundException {
        return personService.findById(id);
    }

    @PutMapping("/{id}")
    public  MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonalNotFoundException {
        return personService.updateById(id,personDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonalNotFoundException {
        personService.delete(id);
    }

}

