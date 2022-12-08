package com.spring.training.mapper;

import com.spring.training.domain.Person;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonContextMapper extends AbstractContextMapper<Person> {

    public Person doMapFromContext(DirContextOperations context) {
        Person person = new Person();
        person.setName(context.getStringAttribute("cn"));
        return person;
    }

    public void mapToContext(Person person, DirContextOperations context) {
        context.setAttributeValues("objectclass", new String[]{"top", "person"});
        context.setAttributeValue("cn", person.getName());
    }

}
