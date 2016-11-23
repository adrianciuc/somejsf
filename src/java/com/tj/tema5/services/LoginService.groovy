package com.tj.tema5.services

import com.tj.tema5.beans.authentication.LoginBean
import com.tj.tema5.model.Person

import java.sql.Connection


interface LoginService {

    Person get(LoginBean loginBean, Connection connection)
}