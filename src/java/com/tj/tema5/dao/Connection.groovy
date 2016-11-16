package com.tj.tema5.dao

import javax.faces.bean.ApplicationScoped
import javax.faces.bean.ManagedBean
import javax.faces.bean.ManagedProperty

@ManagedBean(eager = true)
@ApplicationScoped
class Connection {
    @ManagedProperty(value = "#{dataSource}")
    DataSource dataSource

    java.sql.Connection getConnection() {
        dataSource.dataSource.connection
    }
}
