# Spring LDAP

Spring LDAP is designed to simplify LDAP programming in Java. Some of the features provided by the library are:

- JdbcTemplate-style template simplifications to LDAP programming.

- JPA- or Hibernate-style annotation-based object and directory mapping.

- Spring Data repository support, including support for QueryDSL.

- Utilities to simplify building LDAP queries and distinguished names.

- Proper LDAP connection pooling.

- Client-side LDAP compensating transaction support.

## Setup

```xml
<dependency>
    <groupId>org.springframework.ldap</groupId>
    <artifactId>spring-ldap-core</artifactId>
</dependency>
```