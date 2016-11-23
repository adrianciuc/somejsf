package com.tj.tema5.dao.school

import com.tj.tema5.model.School

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet


class DefaultSchoolRepository implements SchoolRepository {

    @Override
    List<School> getAll(Connection connection) {
        PreparedStatement statement = connection
                .prepareStatement("SELECT * FROM schools")
        ResultSet resultSet = statement.executeQuery()
        List<School> schools = []
        while (resultSet.next()) {
            schools.add(new School(name: resultSet.getString("name")))
        }
        schools
    }
}
