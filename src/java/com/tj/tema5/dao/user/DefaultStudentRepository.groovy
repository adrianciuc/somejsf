package com.tj.tema5.dao.user

import com.tj.tema5.model.Student

import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement

class DefaultStudentRepository extends AbstractPersonRepository<Student> {

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

    @Override
    void chooseSchool(String username, Connection connection, Integer schoolId) {
        Statement statement =
                connection.prepareStatement("UPDATE students SET chosen_school_id = ? WHERE username = ?")
        statement.setInt(1, schoolId)
        statement.setString(2, username)
        statement.executeUpdate()
    }

    @Override
    void add(List<Student> students, Connection connection) {
        students.each {
            add(it, connection)
        }
    }

    @Override
    void add(Student student, Connection connection) {
        Statement statement =
                connection.prepareStatement(
                        "INSERT INTO students ("+
                                " name," +
                                " grade," +
                                " identification_number," +
                                " username," +
                                " password," +
                                " chosen_school_id) VALUES (?,?,?,?,?,?)")
        statement.setString(1, student.name)
        statement.setDouble(2, student.grade)
        statement.setString(3, student.identificationNumber)
        statement.setString(4, student.userName)
        statement.setString(5, student.password)
        statement.setInt(6, student.chosenSchool.id)
        statement.executeUpdate()
    }

    @Override
    Long count(Connection connection) {
        Statement statement = connection.prepareStatement("SELECT count(*) FROM students")
        ResultSet resultSet = statement.executeQuery()
        if (resultSet.next()) {
            resultSet.getLong(1)
        } else {
            0
        }
    }
}
