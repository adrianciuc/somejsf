package com.tj.tema5.beans.authentication

import javax.faces.bean.ManagedBean
import javax.faces.bean.RequestScoped

@ManagedBean
@RequestScoped
class LoginBean {
    String userName
    String password
}
