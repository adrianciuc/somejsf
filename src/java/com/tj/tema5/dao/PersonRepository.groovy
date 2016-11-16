package com.tj.tema5.dao

import java.sql.Connection


interface PersonRepository<Person> {

    Optional<Person> get(String userName, String password, Connection connection)
}