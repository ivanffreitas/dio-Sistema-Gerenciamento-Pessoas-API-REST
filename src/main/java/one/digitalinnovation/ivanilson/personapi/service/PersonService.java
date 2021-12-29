package one.digitalinnovation.ivanilson.personapi.service;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Create person with ID");
    }


    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonalNotFoundException {
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonalNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonalNotFoundException {
        verifyIfExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatePerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatePerson.getId(), "Update person with ID");
    }

    private Person verifyIfExists(Long id) throws PersonalNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(()-> new PersonalNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + "ID: " + id)
                .build();
    }
}
