package com.tj.tema5.services

import com.tj.tema5.beans.LoginBean
import com.tj.tema5.dao.UserRepository
import com.tj.tema5.model.Person

import javax.faces.bean.ApplicationScoped
import javax.faces.bean.ManagedBean
import javax.faces.bean.ManagedProperty

@ManagedBean(eager = true)
@ApplicationScoped
class LoginService {

    @ManagedProperty(value = "#{userRepository}")
    UserRepository userRepository

    public Optional<Person> get(LoginBean loginBean) {
        return Optional.of(userRepository.exists(loginBean.userName, loginBean.password))
    }
}
