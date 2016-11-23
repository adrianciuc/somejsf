package com.tj.tema5.services

import com.tj.tema5.beans.authentication.LoginBean
import com.tj.tema5.dao.user.DefaultAdminRepository
import com.tj.tema5.dao.user.PersonRepository
import com.tj.tema5.dao.user.DefaultStudentRepository
import com.tj.tema5.model.Person

import java.sql.Connection

class DefaultLoginService implements LoginService {
    List<PersonRepository<Person>> usersRepositories

    DefaultLoginService() {
        usersRepositories = new ArrayList<>()
        usersRepositories.add(new DefaultStudentRepository())
        usersRepositories.add(new DefaultAdminRepository())
    }

    public Person get(LoginBean loginBean, Connection connection) {
         usersRepositories.collect {
             it.get loginBean.userName, loginBean.password, connection
         } find()
    }
}
