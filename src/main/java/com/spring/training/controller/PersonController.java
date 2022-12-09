package com.spring.training.controller;

import com.spring.training.domain.Person;
import com.spring.training.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("persons")
@AllArgsConstructor
public class PersonController {

    final PersonService service;

    @GetMapping("{uid}")
    public Mono<Person> getPerson(@PathVariable("uid") String uid) {
        return service.getPerson(uid);
    }

    @GetMapping
    public Flux<Person> getPersons() {
        return service.getPersons();
    }

    @GetMapping("/search/{name}")
    public Flux<Person> getPersons(@PathVariable("name") String name) {
        return service.getPersons(name);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Person> createPerson(@RequestBody Person person) {
        return service.createPerson(person);
    }

    @PutMapping("{uid}")
    public Mono<Person> updatePerson(@PathVariable("uid") String uid, @RequestBody Person person) {
        return service.updatePerson(uid, person);
    }

    @DeleteMapping("{uid}")
    public Mono<Void> deletePerson(@PathVariable("uid") String uid) {
        return service.deletePerson(uid);
    }

}