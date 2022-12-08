package com.spring.training.service;

import com.spring.training.domain.Person;
import com.spring.training.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class PersonService {

    final PersonRepository personRepository;

    public Flux<Person> getPersons() {
        return Flux.fromIterable(personRepository.findAll());
    }

    public Flux<Person> getPersons(String name) {
        return Flux.fromIterable(personRepository.findByName(name));
    }

    public Mono<Person> getPerson(String name, String company, String country) {
        return Mono.just(personRepository.findByPrimaryKey(name, company, country));
    }

    public Mono<Person> createPerson(Person person) {
        return Mono.just(personRepository.create(person));
    }

    public Mono<Person> updatePerson(Person person) {
        return Mono.just(personRepository.update(person));
    }

    public Mono<Void> deletePerson(Person person) {
        return Mono.just(personRepository.delete(person)).then();
    }

}
