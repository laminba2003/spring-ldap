package com.spring.training.repository;

import com.spring.training.domain.Person;
import com.spring.training.mapper.PersonContextMapper;
import lombok.AllArgsConstructor;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Repository;

import javax.naming.Name;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Repository
@AllArgsConstructor
public class PersonRepository {

    final LdapTemplate ldapTemplate;
    final PersonContextMapper personContextMapper;

    public Person create(Person person) {
        DirContextAdapter context = new DirContextAdapter(buildDn(person));
        personContextMapper.mapToContext(person, context);
        ldapTemplate.bind(context);
        return person;
    }

    public Person update(Person person) {
        DirContextOperations context = ldapTemplate.lookupContext(buildDn(person));
        personContextMapper.mapToContext(person, context);
        ldapTemplate.modifyAttributes(context);
        return person;
    }

    public Person delete(Person person) {
        ldapTemplate.unbind(buildDn(person));
        return person;
    }

    public Person findByPrimaryKey(String name, String company, String country) {
        return ldapTemplate.lookup(buildDn(name, company, country), personContextMapper);
    }

    public List<Person> findByName(String name) {
        LdapQuery query = query()
                .where("objectclass").is("person")
                .and("cn").whitespaceWildcardsLike(name);
        return ldapTemplate.search(query, personContextMapper);
    }

    public List<Person> findAll() {
        EqualsFilter filter = new EqualsFilter("objectclass", "person");
        return ldapTemplate.search(LdapUtils.emptyLdapName(), filter.encode(), personContextMapper);
    }

    protected Name buildDn(Person person) {
        return buildDn(person.getName(), person.getCompany(), person.getCountry());
    }

    protected Name buildDn(String name, String company, String country) {
        return LdapNameBuilder.newInstance()
                .add("c", country)
                .add("ou", company)
                .add("cn", name)
                .build();
    }

}