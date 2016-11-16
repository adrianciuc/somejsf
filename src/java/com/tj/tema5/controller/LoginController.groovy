package com.tj.tema5.controller

import com.tj.tema5.beans.AuthenticationBean
import com.tj.tema5.beans.LoginBean
import com.tj.tema5.dao.Connection
import com.tj.tema5.services.LoginService

import javax.faces.bean.ManagedBean
import javax.faces.bean.ManagedProperty
import javax.faces.bean.RequestScoped

//import static com.tj.tema5.dao.DataSource.DATA_SOURCE

@ManagedBean(eager = true)
@RequestScoped
class LoginController {
    LoginService loginService
    @ManagedProperty(value = "#{connection}")
    Connection connection

    LoginController() {
        loginService = new LoginService()
    }

    public String login(LoginBean person, AuthenticationBean authenticationBean) {
        loginService.get person, connection.connection map {
                    authenticationBean.userName = it.userName
                    "home"
        } orElse "login"
    }
}
