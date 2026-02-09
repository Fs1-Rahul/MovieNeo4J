import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/people")
public class PersonController {
    private final PersonRepository personRepo;

    public PersonController(PersonRepository personRepo) {
        this.personRepo = personRepo;
    }

    @GetMapping()
    Iterable<Person> findAllPeople() {
        return personRepo.findAll();
    }

    @GetMapping("/{imdbId}")
    Optional<Person> findPersonById(@PathVariable String imdbId) {
        return personRepo.findById(imdbId);
    }
}