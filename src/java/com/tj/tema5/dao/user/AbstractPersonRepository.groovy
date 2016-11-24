package com.tj.tema5.dao.user

import com.tj.tema5.model.Person

import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement

abstract class AbstractPersonRepository<T extends Person> implements PersonRepository<T> {

    protected abstract String getTableName()

    protected abstract T getEntity(ResultSet resultSet)

    Person get(String userName, String password, Connection connection) {
        Statement statement = connection
                .prepareStatement("SELECT * FROM " + getTableName() + " WHERE username = ? AND password = ?")
        statement.setString(1, userName)
        statement.setString(2, password)
        ResultSet resultSet = statement.executeQuery()
        if (resultSet.next()) {
            getEntity(resultSet)
        }
    }
}
