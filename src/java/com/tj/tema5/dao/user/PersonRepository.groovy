package com.tj.tema5.dao.user

import java.sql.Connection


interface PersonRepository<Person> {

    Person get(String userName, String password, Connection connection)
}