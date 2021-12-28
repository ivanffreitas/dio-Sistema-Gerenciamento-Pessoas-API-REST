package one.digitalinnovation.ivanilson.personapi.repository;

import one.digitalinnovation.ivanilson.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
