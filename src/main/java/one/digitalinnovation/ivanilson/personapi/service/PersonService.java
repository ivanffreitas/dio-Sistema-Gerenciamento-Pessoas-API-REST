package one.digitalinnovation.ivanilson.personapi.service;

import one.digitalinnovation.ivanilson.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.ivanilson.personapi.entity.Person;
import one.digitalinnovation.ivanilson.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(Person person){
        Person savedPerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("ID: "+ savedPerson.getId())
                .build();
    }
}
