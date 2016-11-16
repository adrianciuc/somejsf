package com.tj.tema5.dao

import com.tj.tema5.model.Student

import java.sql.ResultSet

class StudentRepository extends AbstractPersonRepository<Student> {

    @Override
    protected String getTableName() {
        return "students"
    }

    @Override
    protected Student getEntity(ResultSet resultSet) {
        return new Student(
                userName: resultSet.getString("username"),
                name: resultSet.getString("name"),
                grade: resultSet.getDouble("grade"),
                identificationNumber: resultSet.getString("identification_number")
        )
    }
}
