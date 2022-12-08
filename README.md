# Spring LDAP

Spring LDAP is designed to simplify LDAP programming in Java. Some of the features provided by the library are:

- JdbcTemplate-style template simplifications to LDAP programming.

- JPA- or Hibernate-style annotation-based object and directory mapping.

- Spring Data repository support, including support for QueryDSL.

- Utilities to simplify building LDAP queries and distinguished names.

- Proper LDAP connection pooling.

- Client-side LDAP compensating transaction support.

Data is represented in an LDAP system as a hierarchy of objects, each of which is called an entry. The resulting tree structure is called a Directory Information Tree (DIT). The top of the tree is commonly called the root (a.k.a base or the suffix).

To navigate the DIT we can define a path (a DN) to the place where our data is (cn=DEV-India,ou=Distrubition Groups,dc=gp,dc=gl,dc=google,dc=com will take us to a unique entry) or we can define a path (a DN) to where we think our data is (ou=DSI,dc=google,dc=com) then search for the attribute=value or multiple attribute=value pairs to find our target entry (or entries).

## Setup

```xml
<dependency>
    <groupId>org.springframework.ldap</groupId>
    <artifactId>spring-ldap-core</artifactId>
</dependency>
```