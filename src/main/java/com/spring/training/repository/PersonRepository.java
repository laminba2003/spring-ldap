package com.spring.training.repository;

import com.spring.training.config.LdapConfig;
import com.spring.training.domain.Person;
import com.spring.training.mapper.PersonContextMapper;
import lombok.AllArgsConstructor;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.spring.training.mapper.LdapAttributes.buildPersonDn;
import static com.spring.training.mapper.LdapAttributes.getBase;
import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Repository
@AllArgsConstructor
public class PersonRepository {

    final LdapTemplate ldapTemplate;
    final PersonContextMapper personContextMapper;
    final LdapConfig ldapConfig;

    public Person create(Person person) {
        DirContextAdapter context = new DirContextAdapter(buildPersonDn(ldapConfig, person.getUid()));
        personContextMapper.mapToContext(person, context);
        ldapTemplate.bind(context);
        return person;
    }

    public Person update(Person person) {
        DirContextOperations context = ldapTemplate.lookupContext(buildPersonDn(ldapConfig, person.getUid()));
        personContextMapper.mapToContext(person, context);
        ldapTemplate.modifyAttributes(context);
        return person;
    }

    public void delete(String uid) {
        ldapTemplate.unbind(buildPersonDn(ldapConfig, uid));
    }

    public Person findById(String uid) {
        return ldapTemplate.lookup(buildPersonDn(ldapConfig, uid), personContextMapper);
    }

    public List<Person> findAll() {
        EqualsFilter filter = new EqualsFilter("objectclass", "person");
        return ldapTemplate.search(getBase(ldapConfig), filter.encode(), personContextMapper);
    }

    public List<Person> findByName(String name) {
        LdapQuery query = query()
                .base(getBase(ldapConfig))
                .where("objectclass").is("person")
                .and("cn").whitespaceWildcardsLike(name);
        return ldapTemplate.search(query, personContextMapper);
    }

}