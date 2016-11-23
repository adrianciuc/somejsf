package com.tj.tema5.dao.user

import com.tj.tema5.model.Admin

import java.sql.ResultSet

class DefaultAdminRepository extends AbstractPersonRepository<Admin> {

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
