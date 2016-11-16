package com.tj.tema5.dao

import org.postgresql.ds.PGPoolingDataSource

import javax.faces.bean.ApplicationScoped
import javax.faces.bean.ManagedBean

@ManagedBean(eager = true)
@ApplicationScoped
class DataSource {
    javax.sql.DataSource dataSource

    public javax.sql.DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new PGPoolingDataSource();
            dataSource.setDataSourceName("Postgres Data Source");
            dataSource.setServerName("localhost");
            dataSource.setDatabaseName("tema5");
            dataSource.setUser("postgres");
            dataSource.setPassword("04121992an");
            dataSource.setMaxConnections(10);
        }
        return dataSource
    }
}
