package com.tj.tema5.dao.school

import com.tj.tema5.model.School
import com.tj.tema5.model.SchoolPreference

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

import static java.sql.Statement.RETURN_GENERATED_KEYS


class DefaultSchoolRepository implements SchoolRepository {

    SchoolPreferencesRepository preferencesRepository

    DefaultSchoolRepository() {
        preferencesRepository = new DefaultSchoolPreferenceRepository()
    }

    @Override
    List<School> getAll(Connection connection) {
        PreparedStatement statement = connection
                .prepareStatement("SELECT * FROM schools")
        ResultSet resultSet = statement.executeQuery()
        List<School> schools = []
        while (resultSet.next()) {
            SchoolPreference preference =
                    preferencesRepository.getSchoolPreference(resultSet.getInt("id"), connection)
            schools.add(
                    new School(
                            id: resultSet.getInt("id"),
                            name: resultSet.getString("name"),
                            schoolPreferences: preference))
        }
        schools
    }

    @Override
    void add(School school, Connection connection) {
        connection.setAutoCommit(false)
        Statement statement =
                connection.prepareStatement("INSERT INTO schools (name) VALUES (?)", RETURN_GENERATED_KEYS)
        statement.setString(1, school.name)
        statement.executeUpdate()
        ResultSet resultSet = statement.generatedKeys
        if (resultSet.next()) {
            Integer schoolId = resultSet.getInt(1)
            Statement preferencesStatement =
                    connection.prepareStatement("" +
                            "INSERT INTO school_preferences (max_students_allowed, min_grade_allowed, school_id)" +
                            " VALUES (?,?,?)")
            preferencesStatement.setInt(1, school.schoolPreferences.maxStudentAllowed)
            preferencesStatement.setInt(2, school.schoolPreferences.minGradeAllowed)
            preferencesStatement.setInt(3, schoolId)
            preferencesStatement.executeUpdate()
        }
        connection.commit()
    }

    @Override
    Integer getId(String name, Connection connection) {
        Statement statement = connection.prepareStatement("SELECT id FROM schools WHERE name = ?")
        statement.setString(1, name)
        ResultSet resultSet = statement.executeQuery()
        if (resultSet.next()) {
            resultSet.getInt("id")
        }
    }
}
