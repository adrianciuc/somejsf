package com.tj.tema5.dao.user

import com.tj.tema5.model.Admin

import java.sql.Connection
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

    @Override
    void chooseSchool(String username, Connection connection, Integer schoolId) {
        return null
    }

    @Override
    void add(List<Admin> admins, Connection connection) {

    }

    @Override
    void add(Admin student, Connection connection) {

    }

    @Override
    Long count(Connection connection) {
        return null
    }
}
