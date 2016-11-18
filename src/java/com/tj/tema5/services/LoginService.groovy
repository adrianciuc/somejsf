package com.tj.tema5.services

import com.tj.tema5.beans.LoginBean
import com.tj.tema5.dao.AdminRepository
import com.tj.tema5.dao.PersonRepository
import com.tj.tema5.dao.StudentRepository
import com.tj.tema5.model.Person

import java.sql.Connection

class LoginService {
    List<PersonRepository<Person>> usersRepositories

    LoginService() {
        usersRepositories = new ArrayList<>()
        usersRepositories.add(new StudentRepository())
        usersRepositories.add(new AdminRepository())
    }

    public Person get(LoginBean loginBean, Connection connection) {
         usersRepositories.collect {
             it.get loginBean.userName, loginBean.password, connection
         } find()
    }
}
