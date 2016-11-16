package com.tj.tema5.dao

import com.tj.tema5.model.Person

import javax.faces.bean.ApplicationScoped
import javax.faces.bean.ManagedBean
import javax.faces.bean.ManagedProperty
import java.sql.PreparedStatement

@ManagedBean(eager = true)
@ApplicationScoped
class UserRepository {
    @ManagedProperty(value = "#{connection}")
    Connection connection

    public Person exists(String userName, String password) {
        PreparedStatement statement = connection.connection
                .prepareStatement("SELECT * FROM students WHERE username = ? AND password = ?")
        statement.setString(1, userName)
        statement.setString(2, password)
        //TODO: Continue doing stuff
        return new Person()
    }
}
