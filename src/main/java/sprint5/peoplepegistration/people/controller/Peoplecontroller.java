package sprint5.peoplepegistration.people.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.people.controller.facade.PeopleControllerFacade;
import sprint5.peoplepegistration.people.model.mapper.PersonMapper;
import sprint5.peoplepegistration.people.model.request.PersonRequest;
import sprint5.peoplepegistration.people.model.response.PersonResponse;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static sprint5.peoplepegistration.people.model.mapper.PersonMapper.requestPessoa;

@RestController
@RequestMapping("/api/v1/registration")
@AllArgsConstructor
public class Peoplecontroller {
    private final PeopleControllerFacade peopleControllerFacade;

    @GetMapping
    public Flux<PersonResponse> findAll() {
        return peopleControllerFacade.findAll()
                .map(PersonMapper::pessoaResponse);
    }

    @GetMapping("/{id}")
    public Mono<PersonResponse> findById(@PathVariable String id) {
        return peopleControllerFacade.findByID(id)
                .map(PersonMapper::pessoaResponse);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Mono<PersonResponse> create(@RequestBody @Valid PersonRequest personRequest) {
        return peopleControllerFacade.create(requestPessoa(personRequest))
                .map(PersonMapper::pessoaResponse);
    }

    @PutMapping("/{id}")
    public Mono<PersonResponse> update(@PathVariable String id, @RequestBody @Valid PersonRequest personRequest) {
        return peopleControllerFacade.update(id, requestPessoa(personRequest))
                .map(PersonMapper::pessoaResponse);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable String id) {
       return peopleControllerFacade.deleteById(id);
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    public Mono<Void> deleteAll() {
        return peopleControllerFacade.deleteAll();
    }

}
