package com.tj.tema5.controller

import com.tj.tema5.beans.AuthenticationBean
import com.tj.tema5.beans.LoginBean
import com.tj.tema5.services.LoginService

import javax.faces.bean.ManagedBean
import javax.faces.bean.ManagedProperty
import javax.faces.bean.RequestScoped

@ManagedBean(eager = true)
@RequestScoped
class LoginController {
    @ManagedProperty(value = "#{loginService}")
    LoginService loginService

    public String login(LoginBean person, AuthenticationBean authenticationBean) {
        loginService.get person map {
                    authenticationBean.userName = it.userName
                    "some"
        } orElse "login"
    }
}
