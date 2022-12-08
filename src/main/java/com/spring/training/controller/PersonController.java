package com.spring.training.controller;

import com.spring.training.domain.Person;
import com.spring.training.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("persons")
@AllArgsConstructor
public class PersonController {

    final PersonService service;

    @GetMapping("{name}/{company}/{country}")
    public Mono<Person> getPerson(@PathVariable("name") String name, @PathVariable("company") String company, @PathVariable("country") String country) {
        return service.getPerson(name, company, country);
    }

    @GetMapping("{name}")
    public Flux<Person> getPerson(@PathVariable("name") String name) {
        return service.getPersons(name);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Person> getPersons() {
        return service.getPersons();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Person> createPerson(@RequestBody Person person) {
        return service.createPerson(person);
    }

    @PutMapping
    public Mono<Person> updatePerson(@RequestBody Person person) {
        return service.updatePerson(person);
    }

    @DeleteMapping
    public Mono<Void> deletePerson(@RequestBody Person person) {
        return service.deletePerson(person);
    }

}