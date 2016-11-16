package com.tj.tema5.dao

import javax.faces.bean.ManagedBean
import javax.faces.bean.RequestScoped

import static com.tj.tema5.dao.DataSource.DATA_SOURCE

@ManagedBean
@RequestScoped
class Connection {

    java.sql.Connection connection

    java.sql.Connection getConnection() {
        if (!connection) {
            connection = DATA_SOURCE.connection
        }
        connection
    }
}
