package sprint5.peoplepegistration.people.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.people.controller.facade.PeopleControllerFacade;
import sprint5.peoplepegistration.people.model.mapper.PersonMapper;
import sprint5.peoplepegistration.people.model.request.PersonRequest;
import sprint5.peoplepegistration.people.model.response.PersonResponse;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static sprint5.peoplepegistration.people.model.mapper.PersonMapper.requestPessoa;

@RestController
@RequestMapping("/api/v1/registration")
@AllArgsConstructor
@Api(value = "People Registration API")
public class PeopleController {
    private final PeopleControllerFacade peopleControllerFacade;

    @GetMapping
    @ApiOperation("Show all people")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Show all people"),
            @ApiResponse(code = 404, message = "Schema not found"),
            @ApiResponse(code = 400, message = "Missing or invalid request body"),
            @ApiResponse(code = 500, message = "Internal error")})
    public Flux<PersonResponse> findAll() {
        return peopleControllerFacade.findAll()
                .map(PersonMapper::pessoaResponse);
    }

    @GetMapping("/{id}")
    @ApiOperation("Show person by ID")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Show person by ID"),
            @ApiResponse(code = 404, message = "Schema not found"),
            @ApiResponse(code = 400, message = "Missing or invalid request body"),
            @ApiResponse(code = 500, message = "Internal error")})
    public Mono<PersonResponse> findById(@PathVariable String id) {
        return peopleControllerFacade.findByID(id)
                .map(PersonMapper::pessoaResponse);
    }
    @GetMapping("/")
    @ApiOperation("Show person by name")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Show person by name"),
            @ApiResponse(code = 404, message = "Schema not found"),
            @ApiResponse(code = 400, message = "Missing or invalid request body"),
            @ApiResponse(code = 500, message = "Internal error")})
    public Flux<PersonResponse> findByName(@RequestParam(required = false) String nome) {
        return peopleControllerFacade.findByName(nome)
                .map(PersonMapper::pessoaResponse);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Create a person")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Create a person"),
            @ApiResponse(code = 404, message = "Schema not found"),
            @ApiResponse(code = 400, message = "Missing or invalid request body"),
            @ApiResponse(code = 500, message = "Internal error")})
    public Mono<PersonResponse> create(@RequestBody @Valid PersonRequest personRequest) {
        return peopleControllerFacade.create(requestPessoa(personRequest))
                .map(PersonMapper::pessoaResponse);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update a person")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Update a person"),
            @ApiResponse(code = 404, message = "Schema not found"),
            @ApiResponse(code = 400, message = "Missing or invalid request body"),
            @ApiResponse(code = 500, message = "Internal error")})
    public Mono<PersonResponse> update(@PathVariable String id, @RequestBody @Valid PersonRequest personRequest) {
        return peopleControllerFacade.update(id, requestPessoa(personRequest))
                .map(PersonMapper::pessoaResponse);
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Delete a person or all people")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Delete a person or all people"),
            @ApiResponse(code = 404, message = "Schema not found"),
            @ApiResponse(code = 400, message = "Missing or invalid request body"),
            @ApiResponse(code = 500, message = "Internal error")})
    public Mono<Void> deleteAll(@RequestParam(required = false) List<String> ids) {
        return peopleControllerFacade.deleteAllByIDs(ids);
    }

}
