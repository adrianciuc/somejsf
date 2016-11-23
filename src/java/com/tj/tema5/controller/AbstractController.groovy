package com.tj.tema5.controller

import com.tj.tema5.dao.Connection

import javax.faces.bean.ManagedProperty


abstract class AbstractController {
    @ManagedProperty(value = "#{connection}")
    Connection connection
}
