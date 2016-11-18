package com.tj.tema5.beans

import javax.faces.bean.ManagedBean
import javax.faces.bean.SessionScoped


@ManagedBean
@SessionScoped
class AuthenticationBean {
    String userName
    Boolean isAdmin
    String name
}
