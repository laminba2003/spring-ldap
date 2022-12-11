package com.spring.training.mapping;

import com.spring.training.domain.Person;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.stereotype.Component;

import static com.spring.training.mapping.LdapAttributes.*;

@Component
public class PersonContextMapper extends AbstractContextMapper<Person> {

    public Person doMapFromContext(DirContextOperations context) {
        Person person = new Person();
        person.setUid(context.getStringAttribute(UID));
        person.setName(context.getStringAttribute(CN));
        person.setTitle(context.getStringAttribute(TITLE));
        person.setTelephone(context.getStringAttribute(TELEPHONE_NUMBER));
        person.setAddress(context.getStringAttribute(REGISTERED_ADDRESS));
        return person;
    }

    public void mapToContext(Person person, DirContextOperations context) {
        context.setAttributeValues("objectclass", new String[]{"top", "person", "uidObject", "organizationalPerson"});
        context.setAttributeValue(UID, person.getUid());
        context.setAttributeValue(CN, person.getName());
        context.setAttributeValue(SN, person.getLastName());
        context.setAttributeValue(USER_PASSWORD, person.getPassword());
        context.setAttributeValue(TITLE, person.getTitle());
        context.setAttributeValue(REGISTERED_ADDRESS, person.getAddress());
        context.setAttributeValue(TELEPHONE_NUMBER, person.getTelephone());
    }

}
