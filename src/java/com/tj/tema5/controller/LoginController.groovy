package com.tj.tema5.controller

import com.tj.tema5.beans.authentication.AuthenticationBean
import com.tj.tema5.beans.authentication.LoginBean
import com.tj.tema5.dao.Connection
import com.tj.tema5.model.Admin
import com.tj.tema5.services.DefaultLoginService
import com.tj.tema5.services.LoginService

import javax.faces.bean.ManagedBean
import javax.faces.bean.ManagedProperty
import javax.faces.bean.RequestScoped
import javax.faces.context.FacesContext

import static java.util.Optional.ofNullable

@ManagedBean(eager = true)
@RequestScoped
class LoginController extends AbstractController {
    LoginService loginService

    LoginController() {
        loginService = new DefaultLoginService()
    }

    String login(LoginBean person, AuthenticationBean authenticationBean) {
        ofNullable(loginService.get(person, connection.connection)) map {
                    authenticationBean.userName = it.userName
                    authenticationBean.name = it.name
                    authenticationBean.isAdmin = it instanceof Admin
                    "home?faces-redirect=true"
        } orElse "login?faces-redirect=true"
    }

    String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        "login?faces-redirect=true"
    }
}
