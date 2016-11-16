package com.tj.tema5.dao

import org.postgresql.ds.PGPoolingDataSource

class DataSource {
    static final javax.sql.DataSource DATA_SOURCE

    static {
        DATA_SOURCE = new PGPoolingDataSource();
        DATA_SOURCE.setDataSourceName("Postgres Data Source");
        DATA_SOURCE.setServerName("localhost");
        DATA_SOURCE.setDatabaseName("tema5");
        DATA_SOURCE.setUser("postgres");
        DATA_SOURCE.setPassword("04121992an");
        DATA_SOURCE.setMaxConnections(10);
    }
}
