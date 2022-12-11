package com.spring.training.mapping;

import com.spring.training.config.LdapConfig;
import org.springframework.ldap.support.LdapNameBuilder;

import javax.naming.Name;

public interface LdapAttributes {
    String UID = "uid";
    String CN = "cn";
    String SN = "sn";
    String USER_PASSWORD = "userPassword";
    String TITLE = "title";
    String REGISTERED_ADDRESS = "registeredAddress";
    String TELEPHONE_NUMBER = "telephoneNumber";

    static Name buildPersonDn(LdapConfig ldapConfig, String uid) {
        return LdapNameBuilder.newInstance()
                .add(getBase(ldapConfig))
                .add("uid", uid)
                .build();
    }

    static Name getBase(LdapConfig ldapConfig) {
        return LdapNameBuilder.newInstance()
                .add("ou", ldapConfig.getCompany())
                .add("c", ldapConfig.getCountry()).build();
    }
}
