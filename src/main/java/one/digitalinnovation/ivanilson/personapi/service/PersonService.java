package one.digitalinnovation.ivanilson.personapi.service;

import one.digitalinnovation.ivanilson.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.ivanilson.personapi.dto.request.PersonDTO;
import one.digitalinnovation.ivanilson.personapi.entity.Person;
import one.digitalinnovation.ivanilson.personapi.exception.PersonalNotFoundException;
import one.digitalinnovation.ivanilson.personapi.mapper.PersonMapper;
import one.digitalinnovation.ivanilson.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("ID: "+ savedPerson.getId())
                .build();
    }


    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonalNotFoundException {
        Person person = personRepository.findById(id)
                .orElseThrow(()-> new PersonalNotFoundException(id));

        return personMapper.toDTO(person);
    }
}
