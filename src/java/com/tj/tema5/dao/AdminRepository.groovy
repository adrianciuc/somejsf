package com.tj.tema5.dao

import com.tj.tema5.model.Admin

import java.sql.ResultSet

class AdminRepository extends AbstractPersonRepository<Admin> {

    @Override
    protected String getTableName() {
        return "admins"
    }

    @Override
    protected Admin getEntity(ResultSet resultSet) {
        return new Admin(
                userName: resultSet.getString("username"),
                name: resultSet.getString("name"),

        )
    }
}
