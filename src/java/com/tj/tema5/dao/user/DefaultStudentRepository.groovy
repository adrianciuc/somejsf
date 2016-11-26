package com.tj.tema5.dao.user

import com.tj.tema5.model.School
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
                id: resultSet.getInt("id"),
                userName: resultSet.getString("username"),
                name: resultSet.getString("name"),
                grade: resultSet.getDouble("grade"),
                identificationNumber: resultSet.getString("identification_number"),
                chosenSchool: new School(
                        id: resultSet.getInt("chosen_school_id"))
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

    @Override
    List<Student> getStudentsWhoChoosedSchool(Integer schoolId, Connection connection) {
        Statement statemet =
                connection.prepareStatement("SELECT * FROM students WHERE elected_school_id = ?")
        statemet.setInt(1, schoolId)
        ResultSet resultSet = statemet.executeQuery()
        List<Student> students = []
        while (resultSet.next()) {
            students.add(
                    new Student(
                            name: resultSet.getString("name"),
                            identificationNumber: resultSet.getString("identification_number")))
        }
        students
    }

    @Override
    List<Student> getUnrepartitioinedStudents(Connection connection) {
        Statement statement = connection.prepareStatement("SELECT * FROM students WHERE elected_school_id ISNULL ")
        ResultSet resultSet = statement.executeQuery()
        List<Student> students = []
        while (resultSet.next()) {
            students.add(getEntity(resultSet))
        }
        students
    }

    void updateElectedSchools(List<Student> persons, Connection connection) {
        persons.findAll {
          it.electedSchool
        } each {
            updateElectedSchool(it, connection)
        }
    }

    private void updateElectedSchool(Student student, Connection connection) {
        Statement statement = connection.prepareStatement("UPDATE students SET elected_school_id = ? WHERE username = ?")
        statement.setInt(1, student.electedSchool.id)
        statement.setString(2, student.userName)
        statement.executeUpdate()
    }
}
