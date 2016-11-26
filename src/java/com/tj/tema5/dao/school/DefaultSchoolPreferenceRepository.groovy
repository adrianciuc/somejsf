package com.tj.tema5.dao.school

import com.tj.tema5.model.SchoolPreference

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

class DefaultSchoolPreferenceRepository implements SchoolPreferencesRepository {
    @Override
    SchoolPreference getSchoolPreference(Integer schoolId, Connection connection) {
        PreparedStatement statement = connection
                .prepareStatement("SELECT * FROM school_preferences WHERE school_id = ?")
        statement.setInt(1, schoolId)
        ResultSet resultSet = statement.executeQuery()
        if (resultSet.next()) {
            new SchoolPreference(
                    maxStudentAllowed: resultSet.getInt("max_students_allowed"),
                    minGradeAllowed: resultSet.getInt("min_grade_allowed"))
        }
    }

    @Override
    List<SchoolPreference> getAll(Connection connection) {
        Statement statement = connection.prepareStatement("SELECT * FROM school_preferences")
        ResultSet resultSet = statement.executeQuery()
        List<SchoolPreference> preferences = []
        while (resultSet.next()) {
            preferences.add(new SchoolPreference(
                    schoolId: resultSet.getInt("school_id"),
                    maxStudentAllowed: resultSet.getInt("max_students_allowed"),
                    minGradeAllowed: resultSet.getInt("min_grade_allowed")))
        }
        preferences
    }
}
